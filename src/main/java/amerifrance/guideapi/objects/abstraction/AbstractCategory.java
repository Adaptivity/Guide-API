package amerifrance.guideapi.objects.abstraction;

import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.objects.Book;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCategory {

    public List<AbstractEntry> entryList = new ArrayList<AbstractEntry>();
    public String unlocCategoryName;

    public AbstractCategory(List<AbstractEntry> entryList, String unlocCategoryName) {
        this.entryList = entryList;
        this.unlocCategoryName = unlocCategoryName;
    }

    public void addEntry(AbstractEntry entry) {
        this.entryList.add(entry);
    }

    public void removeEntry(AbstractEntry entry) {
        this.entryList.remove(entry);
    }

    public void addEntryList(List<AbstractEntry> entries) {
        this.entryList.addAll(entries);
    }

    public void removeEntryList(List<AbstractEntry> entries) {
        this.entryList.removeAll(entries);
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal(unlocCategoryName);
    }

    public List<String> getTooltip() {
        List<String> list = new ArrayList<String>();
        list.add(getLocalizedName());
        return list;
    }

    @SideOnly(Side.CLIENT)
    public abstract void draw(Book book, int categoryX, int categoryY, int categoryWidth, int categoryHeight, int mouseX, int mouseY, GuiBase guiBase, boolean drawOnLeft, RenderItem renderItem);

    @SideOnly(Side.CLIENT)
    public abstract void drawExtras(Book book, int categoryX, int categoryY, int categoryWidth, int categoryHeight, int mouseX, int mouseY, GuiBase guiBase, boolean drawOnLeft, RenderItem renderItem);

    public abstract boolean canSee(EntityPlayer player, ItemStack bookStack);

    public abstract void onLeftClicked(Book book, int mouseX, int mouseY, EntityPlayer player, ItemStack bookStack);

    public abstract void onRightClicked(Book book, int mouseX, int mouseY, EntityPlayer player, ItemStack bookStack);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCategory)) return false;

        AbstractCategory that = (AbstractCategory) o;

        if (entryList != null ? !entryList.equals(that.entryList) : that.entryList != null) return false;
        if (unlocCategoryName != null ? !unlocCategoryName.equals(that.unlocCategoryName) : that.unlocCategoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entryList != null ? entryList.hashCode() : 0;
        result = 31 * result + (unlocCategoryName != null ? unlocCategoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractCategory{entryList=" + entryList + ", unlocCategoryName='" + unlocCategoryName + '\'' + '}';
    }
}