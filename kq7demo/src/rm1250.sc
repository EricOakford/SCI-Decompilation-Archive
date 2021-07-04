;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1250)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use ExitFeature)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm1250 0
	cryInBowl 1
)

(local
	local0
	local1
)
(instance rm1250 of KQRoom
	(properties
		picture 1250
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
		horizon 65
	)
	
	(method (init)
		(super init:)
		(Bset 21)
		(eNorth init:)
		(eSouth init:)
		(eWest init:)
		(eEast init:)
		(if (and (not demoScripts) (not (ego has: iComb)))
			(ego get: iComb)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						182 76
						180 83
						100 82
						104 89
						147 90
						142 96
						123 100
						123 106
						92 116
						37 108
						37 125
						140 133
						141 137
						148 153
						906 152
						906 111
						884 112
						864 102
						899 89
						851 86
						850 75
						840 83
						809 85
						800 98
						761 97
						680 110
						563 98
						549 84
						565 60
						311 65
						317 80
						302 84
						275 84
						224 83
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						236 89
						261 90
						293 115
						265 130
						226 126
						167 126
						133 116
						130 108
						142 99
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						365 124
						317 125
						315 119
						325 110
						372 111
						383 117
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						367 99
						316 99
						302 88
						355 88
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						483 95
						457 98
						436 98
						414 89
						373 89
						362 82
						400 70
						511 80
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						530 107
						596 112
						629 125
						562 134
						480 123
						484 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						657 137
						700 130
						789 130
						789 137
					yourself:
				)
		)
		(leftCactus init:)
		(rightCactus init:)
		(if
			(and
				(!= prevRoomNum 150)
				(not (ego has: iPeticoat))
				(not (ego has: iFlag))
				(not (Btst 15))
				(not demoScripts)
			)
			(peticoat init:)
		)
		(if
			(and
				(not (ego has: iStick))
				(not (ego has: iFlag))
				(not (Btst 15))
			)
			(stick init:)
		)
		(pictograms init:)
		(well init:)
		(godFace init:)
		(collar init:)
		(hand init:)
		(bowl init:)
		(wristBand init:)
		(waterGod init:)
		(if
			(and
				(not demoScripts)
				(not (ego has: 21))
				(not (Btst 114))
			)
			(saltCrystal init:)
		)
		(pyramidDoor init:)
		(if demoScripts
			(self setScript: demo)
		)
		(UpdatePlane thePlane)
	)
)

(instance demo of Script
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 1303 loop: 1 cel: 0 init: posn: 567 130)
				(if (not scrollingIsOn)
					(self changeState: 4)
				else
					(curRoom setRect: 0 0 959 136)
					(UpdatePlane thePlane)
					(= seconds 4)
				)
			)
			(1
				(= scrollingIsOn TRUE)
				(= temp1 0)
				(= temp2 959)
				(curRoom setRect: 0 0 959 136)
				(UpdatePlane thePlane)
				(if scrollingIsOn
					(curRoom setRect: 0 0 959 136)
					(UpdatePlane thePlane)
					(while (>= (thePlane left:) -250)
						(thePlane setRect: temp1 0 temp2 136)
						(-= temp1 3)
						(-= temp2 3)
						(UpdatePlane thePlane)
						(FrameOut)
					)
				)
				(= local1 temp2)
				(= local0 temp1)
				(self cue:)
			)
			(2
				(if (not scrollingIsOn)
					(RemapColors 3 254 90)
				)
				(if scrollingIsOn
					(ego setCycle: Forward)
				)
				(self cue:)
			)
			(3
				(if (<= (thePlane left:) -319)
					(self cue:)
				else
					(thePlane
						setRect: (-= local0 3) 0 (-= local1 3) 136
					)
					(UpdatePlane thePlane)
					(FrameOut)
					(-= state 1)
					(= ticks 1)
				)
			)
			(4
				(if (not scrollingIsOn)
					(thePlane setRect: -319 0 639 136)
					(UpdatePlane thePlane)
					(RemapColors 2 254 90)
				)
				(ego
					view: 1303
					loop: 1
					cel: 0
					init:
					posn: 567 130
					setCycle: EndLoop self
				)
				(Load RES_WAVE 1304)
			)
			(5
				(ego setLoop: 2 setCel: 0 setCycle: CycleTo 6 1 self)
			)
			(6
				(ego setCycle: CycleTo 12 1 self)
				(if (not (Platform PlatWin))
					(theSoundFX number: 1304 setVol: 127 setLoop: 1 play:)
				)
			)
			(7
				(peticoat init:)
				(ego setCycle: EndLoop self)
			)
			(8 (= ticks 10))
			(9
				(theSoundFX stop:)
				(Load RES_VIEW 1311)
				(Load RES_VIEW 1312)
				(Load RES_VIEW 8134)
				(rat
					view: 1311
					loop: 0
					cel: 0
					x: 427
					y: 69
					init:
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(ego
					view: 8134
					loop: 1
					cel: 0
					init:
					posn: 591 135
					setCycle: EndLoop
				)
			)
			(10
				(rat
					posn: 494 110
					view: 1312
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
				(ego setCycle: BegLoop)
			)
			(11
				(rat dispose:)
				(= ticks 10)
			)
			(12
				(Load RES_WAVE 1313)
				(Load RES_VIEW 835)
				(jackalope init: cycleSpeed: 8 setCycle: CycleTo 11 1 self)
				(ego view: 8134 loop: 1 cel: 0 setCycle: EndLoop)
			)
			(13
				(jackalope setPri: 120 setCel: 12 setCycle: EndLoop self)
				(theSoundFX number: 1313 setLoop: 1 play:)
				(ego setCycle: BegLoop)
			)
			(14
				(jackalope dispose:)
				(ego view: 835 setLoop: 5 setCel: 0 setCycle: CycleTo 7 1 self)
			)
			(15
				(= temp0 100)
				(while (>= temp0 0)
					(Palette PalIntensity 0 255 temp0)
					(FrameOut)
					(-= temp0 5)
				)
				(Load RES_VIEW 1327)
				(Load RES_WAVE 1326)
				(Load RES_WAVE 1327)
				(Load RES_WAVE 824)
				(ego view: 1309 setLoop: 2 setCel: 0 x: 493 y: 93)
				(daRope
					view: 1309
					setCel: 0
					setLoop: 6
					posn: 371 91
					ignoreActors: TRUE
					init:
				)
				(= seconds 2)
			)
			(16
				(= temp0 0)
				(while (<= temp0 100)
					(Palette PalIntensity 0 255 temp0)
					(FrameOut)
					(+= temp0 5)
				)
				(= seconds 1)
			)
			(17
				(jackalope
					view: 1309
					setCel: 0
					setLoop: 4
					posn: 463 74
					setPri: 10
					ignoreActors: TRUE
					init:
					setCycle: CycleTo 15 1 self
				)
			)
			(18
				(if (not (Platform PlatWin))
					(theSoundFX number: 1326 play:)
				)
				(jackalope
					setPri: 120
					posn: 371 91
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
				(daRope hide:)
			)
			(19
				(daRope show:)
				(jackalope
					posn: 369 91
					setLoop: 1
					setCel: 0
					setCycle: CycleTo 7 1 self
				)
				(ego
					view: 1309
					setLoop: 2
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop
				)
			)
			(20
				(jackalope hide:)
				(= ticks 50)
			)
			(21
				(if (not (Platform PlatWin))
					(theSoundFX number: 824 play:)
				)
				(= ticks 30)
			)
			(22
				(jackalope show: setCel: 12 setCycle: EndLoop self)
				(ego setCycle: BegLoop)
			)
			(23
				(if (not (Platform PlatWin))
					(theSoundFX number: 841 play:)
				)
				(jackalope
					posn: 453 116
					view: 1327
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(24
				(if (not (Platform PlatWin))
					(theSoundFX number: 1327 setLoop: 1 play:)
				)
				(jackalope setCel: 0 setLoop: 1 setCycle: CycleTo 11 1 self)
			)
			(25
				(jackalope setCel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(26
				(jackalope dispose:)
				(= seconds 1)
			)
			(27
				(if (not (Platform PlatWin))
					(theSoundFX stop:)
				)
				(curRoom newRoom: 1102)
			)
		)
	)
)

(instance daRope of View)

(instance jackalope of Actor
	(properties
		x 479
		y 78
		view 1305
	)
)

(instance rat of Actor
	(properties
		x 419
		y 69
		view 1311
	)
	
	(method (init)
		(super init:)
		(self setPri: 120)
	)
)

(instance leftCactus of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(super init: &rest)
		(if (or (not (Btst 33)) (Btst 32))
			(self setHotspot: 8 10 25)
		)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						329 12
						321 20
						334 78
						325 72
						318 38
						313 35
						309 41
						321 83
						334 85
						335 92
						348 93
						346 77
						352 63
						346 56
						342 65
						337 44
						346 26
						340 20
						334 25
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(cond 
						((not (Btst 33))
							(ego setScript: touchTheCacti)
						)
						((and (Btst 32) (not (Btst 6)))
							(ego setScript: sayHmmm)
						)
					)
				)
				(25
					(cond 
						((not (Btst 32))
							(Prints {You look at the cacti speculatively.})
						)
						((not (Btst 6))
							(ego setScript: catchTheJackalope)
						)
					)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance jackFur of View
	(properties
		approachX 340
		approachY 95
		x 400
		y 80
		view 990
		loop 1
		cel 4
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 8 10 setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(Prints {You take the fur.})
				(ego get: iHair)
				(self dispose:)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance saltCrystal of View
	(properties
		approachX 141
		approachY 117
		x 155
		y 114
		view 1250
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 8 5 setHotspot: 8 10 5 46)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(ego setScript: lookAtCrystal)
			)
			(5
				(ego setScript: getTheCrystal)
			)
			(46
				(ego setScript: getTheCrystal)
			)
		)
	)
)

(instance rightCactus of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (not (Btst 33))
			(self setHotspot: 8 10 25)
		)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						389 0
						377 7
						372 38
						390 65
						388 80
						401 86
						406 43
						418 0
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(leftCactus doVerb: theVerb)
	)
)

(instance peticoat of Prop
	(properties
		x 562
		y 102
		view 1303
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 setPri: 125)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(ego get: iPeticoat)
				((inventory at: iPeticoat) setHotspot: 0 11)
				(self dispose:)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance cryInBowl of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 126 104 self)
			)
			(1
				(Prints {You cry into the bowl.})
				(Bset 25)
				(= cycles 1)
			)
			(2
				(if (and (Btst 25) (Btst 26) (Btst 27))
					(curRoom setScript: makeFreshWater)
				else
					(theGame handsOn:)
				)
			)
		)
	)
)

(instance getTheStick of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints {You take the stick.})
				(= cycles 1)
			)
			(1
				(ego get: iStick)
				(stick dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance makeFreshWater of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints {The bowl begins to glow, then fades out.})
				(= cycles 1)
			)
			(1
				(Bclr 26)
				(Bset 30)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance touchTheCacti of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 340 95 self)
			)
			(1
				(messager say: 1 8 2 0 self)
			)
			(2
				(Bset 33)
				(leftCactus setHotspot: 0)
				(rightCactus setHotspot: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sayHmmm of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 340 95 self)
			)
			(1
				(messager say: 1 8 3 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance catchTheJackalope of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 340 95 self)
			)
			(1
				(Prints
					{You whip out the rope and stretch it between the cacti.}
				)
				(= cycles 1)
			)
			(2
				(messager say: 1 25 3 1 self)
			)
			(3
				(jackalope
					init:
					posn: 372 86
					view: 1327
					setCel: 0
					setLoop: 0
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: 1 25 3 2 self)
			)
			(5
				(jackalope setCel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(6
				(jackalope
					posn: 356 118
					setCel: 0
					setLoop: 3
					setCycle: EndLoop self
				)
			)
			(7
				(jackalope dispose:)
				(Prints {You get the glasses.})
				(= cycles 1)
			)
			(8
				(jackFur init:)
				(ego get: iSpecs)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lookAtCrystal of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 35)
					(Prints {You try to pry the crystals but no dice.})
					(= cycles 1)
				else
					(Bset 35)
					(messager say: 6 8 8 0 self)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance getTheCrystal of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {You kneel down a pry loose a salt crystal.})
				(= cycles 1)
			)
			(1
				(saltCrystal dispose:)
				(ego get: iSaltCrystal)
				(self dispose:)
			)
		)
	)
)

(instance stick of Feature
	(properties
		nsLeft 227
		nsTop 66
		nsRight 245
		nsBottom 87
		approachX 225
		approachY 88
		x 204
		y 89
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 8 setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(ego setScript: getTheStick)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance pictograms of Feature
	(properties
		nsLeft 82
		nsTop 91
		nsRight 114
		nsBottom 115
		approachX 116
		approachY 102
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 9 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 9)
				(Bset 24)
				(Prints {Show closeup of pictograms.})
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance godFace of Feature
	(properties
		nsLeft 55
		nsRight 94
		nsBottom 26
		y 198
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(if (Btst 10)
					(Bclr 10)
					(Prints {Chg from sun god to rain god.})
				else
					(Bset 10)
					(Prints {Chg from rain god to sun god.})
				)
				(if
				(and (not (Btst 37)) (Btst 31) (Btst 9) (Btst 10))
					(Prints {The well drains.})
					(Bset 37)
				)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance collar of Feature
	(properties
		nsLeft 62
		nsTop 27
		nsRight 83
		nsBottom 34
		approachX 126
		approachY 104
		y 199
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 0 setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(if (Btst 9)
					(Bclr 9)
					(Prints {You scamble the pieces again.})
				else
					(Bset 9)
					(Prints {You quickly line up the turquoise stones.})
				)
				(if
				(and (not (Btst 37)) (Btst 31) (Btst 9) (Btst 10))
					(Prints {The well drains.})
					(Bset 37)
				)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance hand of Feature
	(properties
		noun 3
		nsLeft 80
		nsTop 49
		nsRight 100
		nsBottom 61
		y 200
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 24 30)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(if (Btst 31)
						(Bclr 31)
						(Prints {The bowl is right side up.})
						(super doVerb: theVerb)
					else
						(Bset 31)
						(Prints {The bowl is upside down.})
					)
					(if
					(and (not (Btst 37)) (Btst 31) (Btst 9) (Btst 10))
						(Prints {The well drains.})
						(Bset 37)
					)
					(return 1)
				)
				(24
					(Prints
						{The hand glows momentarily and you take back seed.}
					)
					(return 1)
				)
				(30
					(Prints {The statue takes the corn.})
					(Bset 27)
					(ego put: iCorn)
					(if (and (Btst 25) (Btst 26) (Btst 27))
						(curRoom setScript: makeFreshWater)
					)
					(return TRUE)
				)
			)
		)
	)
)

(instance bowl of Feature
	(properties
		noun 4
		nsLeft 61
		nsTop 67
		nsRight 99
		nsBottom 77
		approachX 126
		approachY 104
		y 201
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 27 28 10 12)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(27
					(Prints {Dump salt water into bowl.})
					(Bset 26)
					(ego put: 15)
					(ego get: 2)
					(if (and (Btst 25) (Btst 26) (Btst 27))
						(curRoom setScript: makeFreshWater)
					)
					(return 1)
				)
				(8
					(if (Btst 26)
						(messager say: noun theVerb 5 0)
					)
					(if (Btst 30)
						(messager say: noun theVerb 6 0)
					)
				)
				(12
					(if (Btst 26)
						(ego put: 2 get: 15)
						(Prints {You get some salt water.})
						(Bclr 26)
					)
					(if (Btst 30)
						(ego put: 2 get: 16)
						(Bclr 30)
						(Prints {You get some fresh water.})
					)
				)
			)
		)
	)
)

(instance well of Feature
	(properties
		nsLeft 144
		nsTop 91
		nsRight 263
		nsBottom 116
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 12)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(if (Btst 37)
						(Prints {You walk into the well.})
						(curRoom newRoom: 1400)
					else
						(Prints {You check out the well full of salt water.})
						(curRoom newRoom: 1400)
					)
				)
				(12
					(Prints {You fill the pot with salt water.})
					(ego put: 2)
					(ego get: 15)
					(return 1)
				)
			)
		)
	)
)

(instance wristBand of Feature
	(properties
		noun 5
		nsLeft 57
		nsTop 73
		nsRight 67
		nsBottom 80
		sightAngle 40
		approachX 77
		approachY 116
		x 62
		y 202
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (or (Btst 26) (Btst 30))
					(messager say: noun theVerb 7 0)
				else
					(Bset 31)
					(Prints {The bowl turns upside down.})
					(if (Btst 9) (Bset 37) (Prints {The well drains.}))
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance waterGod of Feature
	(properties
		approachX 126
		approachY 104
	)
	
	(method (init)
		(super init:)
		(self
			approachVerbs: 8 10
			setHotspot: 8 10
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init:
						51 37
						37 54
						37 93
						86 98
						109 94
						123 65
						121 54
						88 32
						103 23
						104 0
						49 0
						47 25
						62 26
						64 34
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(Prints {You look speculatively at the water god.})
				(self setHotspot: 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pyramidDoor of Feature
	(properties
		nsLeft 816
		nsTop 47
		nsRight 849
		nsBottom 78
		approachX 830
		approachY 78
		y 200
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 approachVerbs: 8)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(curRoom newRoom: 1450)
				(return 1)
			else
				0
			)
		)
	)
)

(instance eNorth of ExitFeature
	(properties
		nsRight 950
		nsBottom 47
		exitDir NORTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((and (& (event type?) walkEvent) (self onMe: event))
					(cond 
						((< (ego x?) 320)
							(curRoom newRoom: 1100)
						)
						((< (ego x?) 640)
							(curRoom newRoom: 1100)
						)
						(else
							(curRoom newRoom: 1100)
						)
					)
				)
				(
				(and scratch (not (event type?)) (self onMe: event))
					(= theExitFeature self)
					((self scratch?) doit:)
					(return (event claimed: TRUE))
				)
			)
		)
	)
)

(instance eSouth of ExitFeature
	(properties
		nsTop 131
		nsRight 950
		nsBottom 136
		exitDir SOUTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(if
		(and (& (event type?) walkEvent) (self onMe: event))
			(cond 
				((< (ego x?) 320)
					(= global177 1)
					(= global176 2)
					(curRoom newRoom: 1500)
				)
				((< (ego x?) 640)
					(= global177 2)
					(= global176 2)
					(curRoom newRoom: 1500)
				)
				(else
					(= global177 3)
					(= global176 2)
					(curRoom newRoom: 1500)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance eWest of ExitFeature
	(properties
		nsRight 20
		nsBottom 140
		exitDir WEST
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(if
		(and (& (event type?) walkEvent) (self onMe: event))
			(= global177 0)
			(= global176 1)
			(curRoom newRoom: 1500)
		else
			(super handleEvent: event)
		)
	)
)

(instance eEast of ExitFeature
	(properties
		nsLeft 929
		nsRight 959
		nsBottom 140
		exitDir 2
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(if
		(and (& (event type?) walkEvent) (self onMe: event))
			(= global177 4)
			(= global176 1)
			(curRoom newRoom: 1500)
		else
			(super handleEvent: event)
		)
	)
)
