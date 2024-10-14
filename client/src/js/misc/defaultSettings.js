export const defaultSettings = {
    legendSettings : [
        {label: "Exclude Assault Legends", 
        condition: function (legend){
            return legend.legendClass.name !== "Assault"
        },
        checked: false
        },
        {label: "Exclude Skirmisher Legends", 
            condition: function (legend){
                return legend.legendClass.name !== "Skirmisher"
            },
            checked: false
        },
        {label: "Exclude Recon Legends", 
            condition: function (legend){
                return legend.legendClass.name !== "Recon"
            },
            checked: false
            },
        {label: "Exclude Support Legends", 
            condition: function (legend){
                 return legend.legendClass.name !== "Support"
            },
            checked: false
        },
        {label: "Exclude Controller Legends", 
            condition: function (legend){
                 return legend.legendClass.name !== "Controller"
            },
            checked: false
        },
        {label: "Exclude Previous Legend", 
            condition: function (legend, prevLegend){
                if(prevLegend.name === undefined){
                    return true
                }
                if(prevLegend.name){
                    return legend.name !== prevLegend.name
                }
                return false;
            },
            checked: false
        },
        {label: "Exclude Previous Legend Class", 
            condition: function (legend, prevLegend){
                if(prevLegend.name){
                    return legend.legendClass.name !== prevLegend.legendClass.name
                }
                return true;
                
            },
            checked: false
        },
        
        
        
    ],
    weaponSettings : [
        {label: "Exclude Care Package Weapons", 
            condition: function (weapon){
                
                return !weapon.isCarePackage
                
            },
            checked: false
        },
        {label: "Exclude Previous Weapons", 
            condition: function (weapon, prevLoadout){
                
                return weapon.name !== prevLoadout.weapon2.name
                
            },
            checked: false
        },
        {label: "Prevent Duplicate Weapons", 
            condition: function (weapon1, weapon2){
                
                return weapon1.name === weapon2.name
                
            },
            checked: false
        },
        {label: "Prevent Duplicate Weapon Ammo", 
            condition: function (weapon1, weapon2){
                
                return weapon1.ammo.name === weapon2.ammo.name
                
            },
            checked: false
        },
    ],
}
