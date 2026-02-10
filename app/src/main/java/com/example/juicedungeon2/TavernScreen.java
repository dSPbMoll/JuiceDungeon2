package com.example.juicedungeon2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
// Importaciones de Flexbox
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.JustifyContent;

public class TavernScreen extends FrameLayout {
    private final Context mContext;
    private FlexboxLayout characterSpawningArea; // Cambiado a FlexboxLayout
    private LinearLayout chosenCharactersLL;
    private Character[] selectedCharacters;
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

    private void init(Context context) {
        this.characterSpawningArea = new FlexboxLayout(context);
        this.chosenCharactersLL = new LinearLayout(context);
        this.selectedCharacters = new Character[6];

        // CONFIGURACIÓN MÁGICA
        this.characterSpawningArea.setFlexDirection(FlexDirection.ROW); // En fila
        this.characterSpawningArea.setFlexWrap(FlexWrap.WRAP);          // Salto de línea automático
        this.characterSpawningArea.setJustifyContent(JustifyContent.FLEX_START); // Alineados al inicio

        addView(characterSpawningArea);
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
                    addCharacterToSpawningArea(Character.SUMMONER);
                    addCharacterToSpawningArea(Character.BERSERKER);
                    addCharacterToSpawningArea(Character.DRUID);
                    addCharacterToSpawningArea(Character.KNIGHT);
                    addCharacterToSpawningArea(Character.GAMBLER);
                    addCharacterToSpawningArea(Character.CORSAIR);
                    addCharacterToSpawningArea(Character.WIZARD);
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

    private void updateChosenCharactersLL() {
        chosenCharactersLL.removeAllViews();

        for (int i=0; i<selectedCharacters.length; i++) {
            if (selectedCharacters[i] != null) break;

            ImageView characterImg = new ImageView(mContext);
            characterImg.setImageResource(getImageResOfCharacter(selectedCharacters[i]));


        }
    }

    private Integer getImageResOfCharacter(Character character) {
        switch (character) {
            case MACIA:
                return R.drawable.macia;
            case SUMMONER:
                return R.drawable.summoner;
            case GAMBLER:
                return R.drawable.gambler;
            case BERSERKER:
                return R.drawable.berserker;
            case WIZARD:
                return R.drawable.wizard;
            case DRUID:
                return R.drawable.druid;
            case CORSAIR:
                return R.drawable.corsair;
            case KNIGHT:
                return R.drawable.knight;
        }
        return null;
    }

}