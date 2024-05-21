package com.xiaoyu.htoolbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class CaffeineTile extends TileService {

    public void onStartListening() {
        super.onStartListening();

        SharedPreferences sp = getSharedPreferences("HToolbox", MODE_PRIVATE);
        Tile tile = getQsTile();
        if (!Settings.System.canWrite(this)) {
            tile.setLabel(getText(R.string.permission_required));
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            try {
                int timeout = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
                int base_timeout = sp.getInt("base_timeout", 2 * 60 * 1000);
                if (timeout == base_timeout)
                    tile.setState(Tile.STATE_INACTIVE);
                else tile.setState(Tile.STATE_ACTIVE);
                if (timeout < 60000)
                    tile.setLabel(getString(R.string.timeout) + timeout / 1000 + "s");
                else tile.setLabel(getString(R.string.timeout) + timeout / 60 / 1000 + "min");
            } catch (Settings.SettingNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        tile.updateTile();
    }

    public void onClick() {
        super.onClick();

        SharedPreferences sp = getSharedPreferences("HToolbox", MODE_PRIVATE);
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
            try {
                int timeout = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
                int base_timeout = sp.getInt("base_timeout", 2 * 60 * 1000);
                int extend_timeout = sp.getInt("extend_timeout", 10 * 60 * 1000);
                if (timeout == base_timeout) {
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_OFF_TIMEOUT,
                            extend_timeout);
                    tile.setState(Tile.STATE_ACTIVE);

                } else {
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_OFF_TIMEOUT,
                            base_timeout);
                    tile.setState(Tile.STATE_INACTIVE);

                }
                timeout = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
                if (timeout < 60000)
                    tile.setLabel(getString(R.string.timeout) + timeout / 1000 + "s");
                else tile.setLabel(getString(R.string.timeout) + timeout / 60 / 1000 + "min");
            } catch (Settings.SettingNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        tile.updateTile();
    }

    public void onStopListening() {
        super.onStopListening();
        SharedPreferences sp = getSharedPreferences((String) getText(R.string.dataset), MODE_PRIVATE);
        Tile tile = getQsTile();
        if (!Settings.System.canWrite(this)) {
            tile.setLabel(getText(R.string.permission_required));
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            try {
                int timeout = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
                int base_timeout = sp.getInt("base_timeout", 2 * 60 * 1000);
                if (timeout == base_timeout)
                    tile.setState(Tile.STATE_INACTIVE);
                else tile.setState(Tile.STATE_ACTIVE);
                if (timeout < 60000)
                    tile.setLabel(getString(R.string.timeout) + timeout / 1000 + "s");
                else tile.setLabel(getString(R.string.timeout) + timeout / 60 / 1000 + "min");
            } catch (Settings.SettingNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        tile.updateTile();
    }
}