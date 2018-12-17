package packet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public TestEnchantment ench = new TestEnchantment(101);

    public void onEnable() {
        LoadEnchantments();
        this.getServer().getPluginManager().registerEvents(ench, this);
    }

    public void LoadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Enchantment.registerEnchantment(ench);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void onDisable() {
        try{
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

            if(byId.containsKey(ench.getId())){
                byId.remove(ench.getId());
            }

            if(byName.containsKey(ench.getName())){
                byName.remove(ench.getName());
            }
        }
        catch (Exception ignored) { }
    }
}
