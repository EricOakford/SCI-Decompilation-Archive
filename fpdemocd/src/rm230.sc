;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh) (include "230.shm")
(use Main)
(use Swing)
(use FPRoom)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Door)
(use ForCount)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm230 0
)

(local
	sepiaPalette
	local1 =  1
	roomCued =  1
)
(instance rm230 of FPRoom
	(properties
		noun N_ROOM
		picture 230
		style FADEOUT
		east 260
		west 220
	)
	
	(method (init)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(switch prevRoomNum
			(west
				(= style SCROLLLEFT)
				(theGame handsOn:)
			)
			(east
				(= style PLAIN)
				(if (and (not (Random 0 5)) (not (Btst fSaloonWindowBroken)))
					(stuntMan init: setCel: 0 setScript: sStuntMan)
				else
					(theGame handsOn:)
				)
			)
			(670
				(ego edgeHit: 0)
			)
			(110
				(LoadMany RES_SCRIPT tlkWilly)
				(curRoom setScript: sSepiaStart)
			)
			(else 
				(ego x: 160 y: 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (== prevRoomNum 670)
			(theMusic1 fade: 63 30 8 0)
		else
			(switch (Random 2 9)
				(2 (= saloonMusic 673))
				(3 (= saloonMusic 673))
				(4 (= saloonMusic 665))
				(5 (= saloonMusic 665))
				(6 (= saloonMusic 666))
				(7 (= saloonMusic 666))
				(8 (= saloonMusic 670))
				(9 (= saloonMusic 670))
			)
			(theMusic1 number: saloonMusic loop: -1 play: setVol: 63)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 118
						309 118
						301 102
						279 102
						276 110
						205 110
						176 112
						134 112
						129 105
						51 105
						51 78
						37 78
						37 105
						0 105
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 174
						319 174
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						8 107
						23 107
						23 118
						8 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						302 109
						302 122
						283 122
						283 109
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						200 112
						215 112
						215 120
						200 120
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						243 111
						258 111
						258 120
						243 120
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						151 114
						151 125
						111 125
						105 123
						64 123
						64 108
						75 108
						76 114
					yourself:
				)
		)
		(cond 
			((Btst fSaloonWindowBroken)
				(fixGlass setScript: sFixGlass)
			)
			((not (cast contains: stuntMan))
				(glass init: addToPic:)
			)
		)
		(saloonWindow init:)
		(saloonWindow2 init:)
		(saloon init:)
		(cafe init:)
		(momsWindow init:)
		(barrel init:)
		(hotelWindow init:)
		(hotelWindow2 init:)
		(hotelWindow3 init:)
		(hotelWindow4 init:)
		(hotelWindow5 init:)
		(saloonDoor init:)
		(hotelDoor init: approachVerbs: V_DO)
		(momsDoor init: approachVerbs: V_DO)
		(bunting init: setOnMeCheck: ftrControl cGREY)
		(clutter init: setOnMeCheck: ftrControl cGREEN)
		(post init: setOnMeCheck: ftrControl cCYAN)
		(hotelBalcony init: setOnMeCheck: ftrControl cMAGENTA)
		(momsPorch init: setOnMeCheck: 1 cBROWN)
		(saloonPorch init: setOnMeCheck: 1 cLGREY)
		(hotelPorch init: setOnMeCheck: ftrControl cRED)
		(sheriff
			init:
			setScale:
			scaleX: 85
			scaleY: 85
			approachVerbs: V_DO V_TALK V_LADDER
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and sepiaPalette (== (PalVary PALVARYINFO) 0))
				(sSepiaStart cue:)
				(= sepiaPalette FALSE)
			)
			(script)
			(
				(and
					(< (ego y?) 108)
					(< 30 (ego x?))
					(< (ego x?) 55)
					(or (< (ego heading?) 46) (> (ego heading?) 314))
					local1
				)
				(= local1 0)
				(saloonDoor doVerb: V_DO)
			)
			((IsObjectOnControl ego cBLUE)
				(curRoom newRoom: 500)
			)
		)
	)
	
	(method (dispose)
		(if (!= newRoomNum 670)
			(theMusic1 fade:)
		)
		(if (fixGlass script?)
			(sFixGlass dispose:)
		)
		(LoadMany FALSE SWING)
		(super dispose:)
	)
	
	(method (cue)
		(= roomCued TRUE)
	)
)

(instance sSepiaStart of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(manBoarding init: approachVerbs: V_DO V_TALK)
				(ego x: -10 y: 150)
				(= sepiaPalette TRUE)
				(PalVary PALVARYSTART 230 0)
				(= seconds 3)
			)
			(1
				(manBoarding setScript: sCloseHotel)
				(PalVary PALVARYREVERSE 8 0 1)
			)
			(2
				(messager say: N_START NULL NULL 0 self)
			)
			(3
				(ego setMotion: MoveTo 140 150 self)
			)
			(4
				(UnLoad RES_VIEW ((ScriptID tlkWilly WILLY) view?))
				(ego setMotion: 0 setHeading: 180 setSpeed: 4)
				(= cycles 20)
			)
			(5
				(theIconBar enable:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseHotel of Script
	
	(method (doit)
		(super doit:)
		(cond 
			((and (not roomCued) (!= (client cel?) 0))
				(= roomCued TRUE)
			)
			(
				(and
					roomCued
					(== (client loop?) 0)
					(== (client cel?) 0)
				)
				(hammerSound number: 2105 play:)
				(= roomCued 0)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(manBoarding setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(manBoarding
					setLoop: 0
					setCel: 0
					setCycle: ForwardCounter 3 self
				)
			)
			(2
				(manBoarding
					setLoop: 2
					setCel: (manBoarding lastCel:)
					setCycle: BegLoop self
				)
			)
			(3 (= ticks 120))
			(4 (self changeState: 0))
			(5 (self dispose:))
		)
	)
)

(instance sStuntMan of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Bset fSaloonWindowBroken)
				(stuntMan cycleSpeed: 9 setCycle: EndLoop self)
				(theMusic2 number: 2231 loop: 1 flags: mNOPAUSE play:)
			)
			(2
				((curRoom obstacles?) delete: beforePol)
				(beforePol dispose:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								131 114
								137 125
								130 136
								123 136
								107 130
								91 137
								83 137
								64 132
								64 123
								64 108
								75 108
								76 114
							yourself:
						)
				)
				(stuntMan
					ignoreActors: TRUE
					setLoop: 1
					posn: 107 135 0
					noun: N_STUNT_GUY
					addToPic:
				)
				(stuntGuy init:)
				(theMusic2
					number: (if (<= currentAct 2) 2925 else 2935)
					loop: 1
					flags: mNOPAUSE
					play:
				)
				(saloonWindow2 noun: N_BROKEN_WINDOW)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFixGlass of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fixGlass init: approachVerbs: V_DO V_TALK)
				(= seconds 3)
			)
			(1
				(Bclr fSaloonWindowBroken)
				(fixGlass cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2 (= seconds 4))
			(3
				(glass init:)
				(fixGlass setLoop: 4 setCel: 1 setPri: -1 posn: 106 106)
				(= cycles 2)
			)
			(4
				(fixGlass
					setLoop: 1
					posn: 103 106
					cycleSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 152 119 self
				)
			)
			(5
				(fixGlass setLoop: 2 setMotion: PolyPath 134 137 self)
			)
			(6
				(fixGlass setMotion: PolyPath -15 137 self)
			)
			(7 (self dispose:))
		)
	)
)

(instance sCloseSaloonDoors of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(saloonDoor setPri: 6 setCel: 3 setCycle: CycleTo 6 1 self)
			)
			(1
				(ego
					posn: (saloonDoor moveToX?) (saloonDoor moveToY?)
					setMotion: PolyPath (saloonDoor approachX?) (saloonDoor approachY?) self
				)
			)
			(2
				(doorSound number: (saloonDoor openSnd?) play:)
				(saloonDoor setPri: 5 setCycle: Swing 1 1 0 1 self)
			)
			(3
				(saloonDoor doorState: doorClosing cue:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenSaloonDoors of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (saloonDoor moveToX?) (saloonDoor moveToY?) self
				)
			)
			(1
				(ego setHeading: 0)
				(saloonDoor setPri: 7 setCycle: Swing 1 1 0 1 self)
			)
			(2
				(saloonDoor cue:)
				(self dispose:)
			)
		)
	)
)

(instance fixGlass of Actor
	(properties
		x 106
		y 109
		noun N_BILLY
		view 232
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_FIX_GLASS)
			)
			(V_TALK
				(messager say: noun theVerb C_FIX_GLASS)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance manBoarding of Prop
	(properties
		x 309
		y 112
		noun N_BILLY
		approachX 283
		approachY 129
		view 110
		loop 2
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_MAN_BOARDING)
			)
			(V_TALK
				(messager say: noun theVerb C_MAN_BOARDING)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance stuntMan of Prop
	(properties
		x 103
		y 1
		z -89
		view 231
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance sheriff of View
	(properties
		x 133
		y 120
		noun 30
		approachX 159
		approachY 124
		view 230
		loop 2
		signal (| ignrAct stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(switch sheriffTalkCount
					(0
						(messager say: noun theVerb C_TALK1)
						(++ sheriffTalkCount)
					)
					(1
						(messager say: noun theVerb C_TALK3)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance momsDoor of View
	(properties
		x 140
		y 93
		noun N_MOM_DOOR
		approachX 147
		approachY 106
		view 230
		loop 1
	)
)

(instance saloonDoor of Door
	(properties
		x 40
		y 94
		noun N_SALOON_DOOR
		approachX 42
		approachY 115
		view 230
		cel 3
		priority 5
		signal fixPriOn
		entranceTo 670
		lockedCase C_SALOON_CLOSED
		openSnd 2911
		closeSnd 2911
		openVerb V_DO
		moveToX 40
		moveToY 101
		enterType 0
		exitType 0
	)
	
	(method (init)
		(= openScript sOpenSaloonDoors)
		(= closeScript sCloseSaloonDoors)
		(super init:)
		(= cel 3)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= local1 0)
				(ego edgeHit: NORTH)
				(curRoom north: 670)
				(super doVerb: theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (open)
		(if locked
			(super open:)
		else
			(if (user controls?) (theGame handsOff:))
			(= doorState doorOpening)
			(self setCycle: CycleTo 0 -1 self)
			(if (self openSnd?)
				(doorSound number: (self openSnd?) play:)
			)
		)
	)
	
	(method (createPoly)
		(super createPoly:
			28 103
			28 100
			57 100
			57 103
		)
	)
)

(instance glass of View
	(properties
		x 103
		y 90
		noun N_GLASS
		view 231
		loop 2
		signal (| ignrAct stopUpdOn)
	)
)

(instance beforePol of Polygon)

(instance stuntGuy of Feature
	(properties
		x 91
		y 129
		noun N_STUNT_GUY
		nsTop 124
		nsLeft 67
		nsBottom 135
		nsRight 129
	)
)

(instance saloonWindow of Feature
	(properties
		x 76
		y 78
		noun N_GLASS
		nsTop 67
		nsLeft 63
		nsBottom 91
		nsRight 89
	)
)

(instance saloonWindow2 of Feature
	(properties
		x 103
		y 79
		noun N_GLASS
		nsTop 67
		nsLeft 90
		nsBottom 91
		nsRight 116
	)
)

(instance saloon of Feature
	(properties
		x 65
		y 22
		noun N_SALOON
		nsTop -1
		nsLeft 34
		nsBottom 46
		nsRight 97
	)
)

(instance cafe of Feature
	(properties
		x 163
		y 25
		noun N_CAFE
		nsTop 5
		nsLeft 128
		nsBottom 46
		nsRight 198
	)
)

(instance momsWindow of Feature
	(properties
		x 181
		y 74
		noun N_MOM_WINDOW
		nsTop 65
		nsLeft 170
		nsBottom 84
		nsRight 193
	)
)

(instance barrel of Feature
	(properties
		x 119
		y 114
		noun N_BARREL
		nsTop 104
		nsLeft 110
		nsBottom 124
		nsRight 129
	)
)

(instance hotelWindow of Feature
	(properties
		x 236
		y 30
		noun N_HOTEL_WINDOW
		nsTop 17
		nsLeft 229
		nsBottom 43
		nsRight 244
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: noun theVerb C_CANT_REACH)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hotelWindow2 of Feature
	(properties
		x 271
		y 29
		noun N_HOTEL_WINDOW
		nsTop 15
		nsLeft 263
		nsBottom 43
		nsRight 280
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: noun theVerb C_CANT_REACH)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hotelWindow3 of Feature
	(properties
		x 311
		y 30
		noun N_HOTEL_WINDOW
		nsTop 17
		nsLeft 304
		nsBottom 43
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: noun theVerb C_CANT_REACH)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hotelWindow4 of Feature
	(properties
		x 241
		y 79
		noun N_HOTEL_WINDOW
		nsTop 65
		nsLeft 212
		nsBottom 93
		nsRight 270
	)
)

(instance hotelWindow5 of Feature
	(properties
		x 316
		y 79
		noun N_HOTEL_WINDOW
		nsTop 65
		nsLeft 313
		nsBottom 93
		nsRight 319
		approachX 310
		approachY 114
	)
)

(instance hotelDoor of Feature
	(properties
		x 291
		y 82
		noun N_HOTEL_DOOR
		nsTop 66
		nsLeft 276
		nsBottom 99
		nsRight 306
		approachX 289
		approachY 105
	)
)

(instance clutter of Feature
	(properties
		x 160
		y 180
		noun N_CLUTTER
		onMeCheck cGREEN
	)
)

(instance post of Feature
	(properties
		x 100
		y 90
		noun N_POST
		onMeCheck cCYAN
	)
)

(instance hotelPorch of Feature
	(properties
		x 190
		y 80
		noun N_HOTEL_PORCH
		onMeCheck cRED
		approachX 235
		approachY 129
	)
)

(instance hotelBalcony of Feature
	(properties
		x 190
		y 60
		noun N_HOTEL_BALCONY
		onMeCheck cMAGENTA
	)
)

(instance bunting of Feature
	(properties
		x 271
		y 75
		noun N_BUNTING
		onMeCheck cGREY
	)
)

(instance momsPorch of Feature
	(properties
		x 160
		y 80
		noun N_MOM_PORCH
		onMeCheck cBROWN
	)
)

(instance saloonPorch of Feature
	(properties
		x 80
		y 80
		noun N_SALOON_PORCH
		onMeCheck cLGREY
	)
)

(instance doorSound of FPSound)

(instance hammerSound of FPSound
	(properties
		flags mNOPAUSE
	)
)
