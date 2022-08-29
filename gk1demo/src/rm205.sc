;;; Sierra Script 1.0 - (do not remove this comment)
(script# 205)
(include game.sh) (include "205.shm")
(use Main)
(use Procs)
(use MapIcons)
(use Print)
(use Polygon)
(use Window)
(use Game)
(use System)

(public
	rm205 0
	greaterMap 1
	tmp2Code 2
	delay 3
)

(local
	landmarkName
	mapPoly
	goToDestination
)
(instance rm205 of Room
	(properties
		picture 205
		style FADEOUT
	)
	
	(method (init)
		(Load RES_SCRIPT 20)
		(greaterMap add: frenchQ)
		(if (Btst fUsedCopRadio)
			(greaterMap add: lake)
		)
		(if (Btst fFlag100)
			(greaterMap add: grandma)
		)
		(= mapPoly
			((Polygon new:)
				type: PTotalAccess
				init:
					38 62
					58 74
					74 109
					127 81
					142 88
					142 121
					157 127
					188 124
					205 115
					206 97
					217 86
					285 115
					279 125
					248 107
					219 95
					217 122
					162 135
					134 125
					134 93
					89 119
					62 113
					41 76
					16 74
					17 64
				yourself:
			)
		)
		(self setScript: delay)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if (not script)
			(greaterMap doit:)
		)
	)
	
	(method (dispose)
		(greaterMap dispose:)
		(mapPoly dispose:)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(DisposeScript 20)
		(theGame handsOn:)
		(super dispose:)
	)
	
	(method (cue whoCares)
		(self setScript: chgRoom 0 whoCares)
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

(instance delay of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(greaterMap eachElementDo: #init show:)
				(self dispose:)
			)
		)
	)
)

(instance greaterMap of IconMap
	
	(method (noDest)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(NOmapWindow color: 7)
		(destPrint
			addText: N_ROOM NULL C_PLEASE_SELECT 0 0 0 205
			x: 0
			y: 150
			window: NOmapWindow
			modeless: TRUE
			init:
		)
	)
)

(instance grandma of MapIcon
	(properties
		view 205
		loop 1
		cel 0
		nsLeft 167
		nsTop 47
		noun N_GRANDMA
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 380)
	)
	
	(method (select)
		(return
			(if isDemo
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(NOmapWindow color: 7)
				(Print
					addText: N_NO_GO_GRANDMA NULL NULL 0 0 0 205
					x: 0
					y: 150
					window: NOmapWindow
					modeless: TRUE
					init:
				)
				(return FALSE)
			else
				FALSE
			)
		)
	)
)

(instance frenchQ of MapIcon
	(properties
		view 205
		loop 6
		cel 0
		nsLeft 191
		nsTop 64
		noun N_FRENCH_QUARTER
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 200)
	)
)

(instance lake of MapIcon
	(properties
		view 205
		loop 2
		cel 0
		nsLeft 145
		nsTop 40
		noun N_LAKE
	)
	
	(method (doit)
		(theGame setCursor: HAND_CURSOR TRUE)
		(curRoom cue: 470)
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

(instance tmp2Code of Code
	
	(method (doit theX theY)
		(cond 
			((InRect 146 99 164 121 theX theY)
				(if (!= landmarkName 2)
					(if modelessDialog (modelessDialog dispose:))
					(= landmarkName 2)
					(NOmapWindow color: 55)
					(Print
						addText: N_AUDUBON_PARK NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 180 77 197 97 theX theY)
				(if (!= landmarkName 4)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= landmarkName 4)
					(Print
						addText: N_CENTRAL_BUSINESS NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 182 37 192 57 theX theY)
				(if (!= landmarkName 5)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= landmarkName 5)
					(Print
						addText: N_CITY_PARK NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 171 101 189 122 theX theY)
				(if (!= landmarkName 6)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= landmarkName 6)
					(Print
						addText: N_GARDEN_DISTRICT NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((InRect 94 22 276 34 theX theY)
				(if (!= landmarkName 7)
					(if modelessDialog (modelessDialog dispose:))
					(NOmapWindow color: 55)
					(= landmarkName 7)
					(Print
						addText: N_PONTCHARTRAIN NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			((AvoidPath theX theY mapPoly)
				(if (!= landmarkName 8)
					(if modelessDialog (modelessDialog dispose:))
					(= landmarkName 8)
					(NOmapWindow color: 55)
					(Print
						addText: N_MISSISSIPPI NULL NULL 0 0 0 205
						x: 0
						y: 150
						window: NOmapWindow
						modeless: TRUE
						init:
					)
				)
			)
			(else
				(= landmarkName 0)
				(if (and (not goToDestination) modelessDialog)
					(modelessDialog dispose:)
				)
			)
		)
	)
)
