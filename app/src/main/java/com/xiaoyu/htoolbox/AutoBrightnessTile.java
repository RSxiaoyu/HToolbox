package com.xiaoyu.htoolbox;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;


public class AutoBrightnessTile extends TileService {

    public void onStartListening() {
        super.onStartListening();
        updateTile();
    }

    public void onClick() {
        super.onClick();
        Tile tile = getQsTile();
        if (!Settings.System.canWrite(this)) {
            tile.setLabel(getText(R.string.permission_required));
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 添加这个标志
            startActivity(intent);
        } else {
            int mode = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
            );
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                );
                tile.setLabel(getText(R.string.autobrightness_off));
                tile.setState(Tile.STATE_INACTIVE);
            } else {
                Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
                );
                tile.setLabel(getText(R.string.autobrightness_on));
                tile.setState(Tile.STATE_ACTIVE);
            }
            tile.updateTile();
        }
    }

    public void onStopListening() {
        super.onStopListening();
        updateTile();

    }

    public void updateTile() {
        Tile tile = getQsTile();
        if (!Settings.System.canWrite(this)) {
            tile.setLabel(getText(R.string.permission_required));
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            int mode = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
            );
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                tile.setLabel(getText(R.string.autobrightness_on));
                tile.setState(Tile.STATE_ACTIVE);
            } else {
                tile.setLabel(getText(R.string.autobrightness_off));
                tile.setState(Tile.STATE_INACTIVE);
            }
        }
        tile.updateTile();
    }
}
