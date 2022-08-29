;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include "200.shm")
(use Main)
(use Procs)
(use MapIcons)
(use Print)
(use Window)
(use Game)
(use User)
(use System)

(public
	rm200 0
	cityMap 1
	tmpCode 2
)

(local
	streetNumber
	goToDestination
)
(instance rm200 of Room
	(properties
		picture 200
		style FADEOUT
	)
	
	(method (init)
		(Load RES_SCRIPT 2)
		(cityMap
			add:
				bookStore
				jackSquare
				louisCath
				napHouse
				greatNO1
				greatNO2
				voodooShop
				voodooMus
		)
		(if (Btst fFlag63)
			(cityMap add: policeLobby)
		)
		(super init:)
		(self setScript: delay)
	)
	
	(method (doit)
		(super doit:)
		(if (not script)
			(cityMap doit:)
		)
	)
	
	(method (dispose)
		(cityMap dispose:)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(DisposeScript 20)
		(User canInput: FALSE)
		(theGame handsOn:)
		(super dispose:)
	)
	
	(method (cue whoCares)
		(self setScript: chgRoom 0 whoCares)
	)
)

(instance delay of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(cityMap eachElementDo: #init show:)
				(self dispose:)
			)
		)
	)
)

(instance chgRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 1)
			)
			(1
				(curRoom newRoom: register)
			)
		)
	)
)

(instance cityMap of IconMap
	
	(method (noDest)
		(if modelessDialog (modelessDialog dispose:))
		(NOmapWindow color: 7)
		(destPrint
			addText: N_ROOM NULL C_PLEASE_SELECT 0 0 0 200
			x: 0
			y: 150
			window: NOmapWindow
			modeless: TRUE
			init:
		)
	)
)

(instance bookStore of MapIcon
	(properties
		view 200
		loop 0
		cel 0
		nsLeft 181
		nsTop 67
		noun N_BOOK_STORE
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 210)
	)
)

(instance jackSquare of MapIcon
	(properties
		view 200
		loop 2
		cel 0
		nsLeft 154
		nsTop 122
		noun N_JACKSON_SQUARE
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 430)
	)
)

(instance louisCath of MapIcon
	(properties
		view 200
		loop 1
		cel 0
		nsLeft 152
		nsTop 96
		noun N_CATHEDRAL
	)
	
	(method (select)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(NOmapWindow color: 7)
		(Print
			addText: N_NO_GO_DEMO NULL NULL 0 0 0 200
			x: 0
			y: 150
			window: NOmapWindow
			modeless: TRUE
			init:
		)
		(return FALSE)
	)
)

(instance policeLobby of MapIcon
	(properties
		view 200
		loop 4
		cel 0
		nsLeft 85
		nsTop 107
		noun N_POLICE
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 230)
	)
)

(instance napHouse of MapIcon
	(properties
		view 200
		loop 10
		cel 0
		nsLeft 122
		nsTop 122
		noun N_NAPOLEON_HOUSE
	)
	
	(method (select)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(NOmapWindow color: 7)
		(Print
			addText: N_NO_GO_DEMO NULL C_NO_GO_NAPOLEON 0 0 0 200
			x: 0
			y: 150
			window: NOmapWindow
			modeless: TRUE
			init:
		)
		(return FALSE)
	)
)

(instance greatNO1 of MapIcon
	(properties
		view 200
		loop 11
		cel 0
		nsTop 32
		nsRight 37
		nsBottom 142
		noun N_GREATER_NEW_ORLEANS
		drawIt 0
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 205)
	)
)

(instance greatNO2 of MapIcon
	(properties
		view 200
		loop 11
		cel 0
		nsLeft 284
		nsTop 32
		nsRight 319
		nsBottom 142
		noun N_GREATER_NEW_ORLEANS
		drawIt 0
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 205)
	)
)

(instance voodooShop of MapIcon
	(properties
		view 200
		loop 5
		cel 0
		nsLeft 262
		nsTop 56
		noun N_VOODOO_SHOP
	)
	
	(method (doit)
		(if (not isDemo)
			(theGame setCursor: HAND_CURSOR TRUE)
			(curRoom cue: 250)
		)
	)
	
	(method (select)
		(return
			(if isDemo
				(if modelessDialog (modelessDialog dispose:))
				(NOmapWindow color: 7)
				(Print
					addText: N_NO_GO_DEMO NULL C_NO_GO_VOODOO_SHOP 0 0 0 200
					x: 0
					y: 150
					window: NOmapWindow
					modeless: TRUE
					init:
				)
				(return FALSE)
			else
				0
			)
		)
	)
)

(instance voodooMus of MapIcon
	(properties
		view 200
		loop 6
		cel 0
		nsLeft 224
		nsTop 119
		noun N_MUSEUM
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 260)
	)
)

(instance NOmapWindow of SysWindow
	(properties
		back 0
	)
)

(instance destPrint of Print
	
	(method (init)
		(= goToDestination TRUE)
		(super init:)
	)
	
	(method (dispose)
		(= goToDestination FALSE)
		(super dispose:)
	)
)

(instance tmpCode of Code
	
	(method (doit theX theY)
		(cond 
			((InRect 35 28 138 36 theX theY)
				(if (!= streetNumber 1)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 1)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_BASIN 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 36 41 313 46 theX theY)
				(if (!= streetNumber 2)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 2)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_N_RAMPART 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 40 59 307 63 theX theY)
				(if (!= streetNumber 3)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 3)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_BURGUNDY 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 40 75 296 79 theX theY)
				(if (!= streetNumber 4)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 4)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_DAUPHINE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 40 90 280 94 theX theY)
				(if (!= streetNumber 5)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 5)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_BOURBON 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 39 104 307 108 theX theY)
				(if (!= streetNumber 6)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= streetNumber 6)
					(Print
						addText: N_STREETS NULL C_ROYAL 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 41 120 287 124 theX theY)
				(if (!= streetNumber 7)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= streetNumber 7)
					(Print
						addText: N_STREETS NULL C_CHARTRES 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 0 137 280 141 theX theY)
				(if (!= streetNumber 8)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= streetNumber 8)
					(Print
						addText: N_STREETS NULL C_DECATUR 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 40 29 47 141 theX theY)
				(if (!= streetNumber 9)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= streetNumber 9)
					(Print
						addText: N_STREETS NULL C_CANAL 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 63 29 67 141 theX theY)
				(if (!= streetNumber 10)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= streetNumber 10)
					(Print
						addText: N_STREETS NULL C_IBERVILLE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 79 29 84 141 theX theY)
				(if (!= streetNumber 11)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 11)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_BIENVILLE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 98 29 102 141 theX theY)
				(if (!= streetNumber 12)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 12)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_CONTI 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 116 29 120 141 theX theY)
				(if (!= streetNumber 13)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 13)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ST_LOUIS 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 134 36 149 141 theX theY)
				(if (!= streetNumber 14)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 14)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_TOULOUSE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 151 34 156 141 theX theY)
				(if (!= streetNumber 15)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 15)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ST_PETER 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 162 47 165 141 theX theY)
				(if (!= streetNumber 16)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 16)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ORLEANS 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 170 47 175 141 theX theY)
				(if (!= streetNumber 17)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 17)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ST_ANN 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 189 47 194 141 theX theY)
				(if (!= streetNumber 18)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 18)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_DUMAINE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 207 30 212 141 theX theY)
				(if (!= streetNumber 19)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 19)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ST_PHILIP 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 224 30 230 141 theX theY)
				(if (!= streetNumber 20)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 20)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_URSULINES 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 243 30 247 141 theX theY)
				(if (!= streetNumber 21)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 21)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_GOVENOR_NICHOLLS 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 261 30 266 141 theX theY)
				(if (!= streetNumber 22)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 22)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_BARRACKS 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 273 30 280 141 theX theY)
				(if (!= streetNumber 23)
					(if modelessDialog (modelessDialog dispose:))
					(= streetNumber 23)
					(NOmapWindow color: 55)
					(Print
						addText: N_STREETS NULL C_ESPLANADE 0 0 0 200
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			(else
				(= streetNumber 0)
				(if (and (not goToDestination) modelessDialog)
					(modelessDialog dispose:)
				)
			)
		)
	)
)
