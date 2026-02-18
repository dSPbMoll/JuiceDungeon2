package com.example.juicedungeon2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
// Importaciones de Flexbox
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

public class TavernScreen extends FrameLayout {
    private final Context mContext;
    private FlexboxLayout characterSpawningArea;
    private LinearLayout teamLL;
    private LinearLayout.LayoutParams teamLLChildParams;
    private ArrayList<Character> team;
    private final int maxTeamSize = 6;
    private int width;
    private int height;

    public TavernScreen(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public TavernScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public void setTeamLayout(LinearLayout layout) {
        this.teamLL = layout;
    }

    private void init(Context context) {
        this.characterSpawningArea = new FlexboxLayout(context);
        this.teamLLChildParams = initTeamLLChildParams();
        this.team = new ArrayList<>();

        // CONFIGURACIÓN MÁGICA
        this.characterSpawningArea.setFlexDirection(FlexDirection.ROW);
        this.characterSpawningArea.setFlexWrap(FlexWrap.WRAP);
        this.characterSpawningArea.setJustifyContent(JustifyContent.FLEX_START);

        addView(characterSpawningArea);
    }

    private LinearLayout.LayoutParams initTeamLLChildParams() {
        int sizeInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                50,
                getResources().getDisplayMetrics()
        );
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
        params.setMargins(8, 8, 8, 8);
        return params;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            width = w;
            height = h;
            this.post(new Runnable() {
                @Override
                public void run() {
                    characterSpawningArea.removeAllViews();
                    updateCharacterSpawningArea(width, height);

                    addCharacterToSpawningArea(Character.MACIA);
                    addCharacterToSpawningArea(Character.WIZARD);
                    addCharacterToSpawningArea(Character.KNIGHT);
                }
            });
        }
    }

    private void updateCharacterSpawningArea(int currentWidth, int currentHeight) {
        int posX = (int) Math.round(currentWidth / 18.5 * 8);
        int posY = (int) Math.round(currentHeight / 10.5 * 4);
        int sizeX = currentWidth - posX;
        int sizeY = currentHeight - posY;

        if (sizeX < 0) sizeX = 0;
        if (sizeY < 0) sizeY = 0;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(sizeX, sizeY);
        params.leftMargin = posX;
        params.topMargin = posY;
        characterSpawningArea.setLayoutParams(params);
    }

    private void addCharacterToSpawningArea(Character character) {
        Integer imageResId = getImageResOfCharacter(character);
        if (imageResId == null) return;

        if (imageResId != 0) {
            ImageView characterImg = new ImageView(mContext);
            characterImg.setImageResource(imageResId);
            characterImg.setId(team.size());
            characterImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCharacterToTeamLL(character, v.getId());
                }
            });

            //int charH = (int) Math.round(height / 10.5 * 2);
            int charH = (int) Math.round(height /4);

            // IMPORTANTE: Usar FlexboxLayout.LayoutParams
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    charH
            );
            // Márgenes pequeños para que no se peguen
            params.setMargins(5, 5, 5, 5);

            characterImg.setLayoutParams(params);
            characterImg.setAdjustViewBounds(true); // Ajustar borde a la imagen
            characterImg.setScaleType(ImageView.ScaleType.FIT_CENTER);

            characterSpawningArea.addView(characterImg);
        }
    }

    private void addCharacterToTeamLL(Character character, int characterPosition) {
        if (team.size() < maxTeamSize) {
            ImageView imageView = new ImageView(mContext);
            int charRes = getImageResOfCharacter(character);
            imageView.setImageResource(charRes);
            imageView.setLayoutParams(teamLLChildParams);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    team.remove(characterPosition);
                    teamLL.removeView(v);
                }
            });

            teamLL.addView(imageView);
            team.add(character);
        }
    }

    private Integer getImageResOfCharacter(Character character) {
        switch (character) {
            case MACIA:
                return R.drawable.macia;
            case WIZARD:
                return R.drawable.wizard;
            case KNIGHT:
                return R.drawable.knight;
        }
        return null;
    }

}