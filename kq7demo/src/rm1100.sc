;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1100)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use ExitFeature)
(use Print)
(use Polygon)
(use Feature)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm1100 0
)

(instance rm1100 of KQRoom
	(properties
		picture 1100
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						687 88
						733 104
						732 118
						700 120
						658 99
					yourself:
				)
		)
		(Load RES_MESSAGE 1100)
		(Bset 21)
		(self south: 1250)
		(eSouth init:)
		(eWest init:)
		(eEast init:)
		(if (Btst 43)
			(cornPlant init:)
		)
		(head init:)
		(if (not (Btst 29))
			(prickly init:)
		)
		(gourd init:)
		(if (not (Btst 16))
			(wetSand init:)
		)
		(glyphs init:)
		(cave init:)
		(door init:)
		(hole init:)
		(theSign init:)
		(if (Btst 7)
			(if (not (ego has: iHair))
				(hair init:)
			)
			(if (and (not (Btst 6)) (not (ego has: 9)))
				(specs init:)
			)
		)
		(switch prevRoomNum
			(1250
				(cond 
					((< (ego x?) 320) (self setRect: 0 0 959 136) (ego init: posn: 160 130))
					((< (ego x?) 640) (self setRect: -319 0 639 136) (ego init: posn: 480 130))
					(else (self setRect: -639 0 319 136) (ego init: posn: 800 130))
				)
			)
			(1500
				(cond 
					((and (== global176 0) (== global177 1))
						(self setRect: 0 0 959 136)
						(ego init: posn: 80 120)
						(theGame handsOn:)
					)
					((and (== global176 0) (== global177 3))
						(self setRect: -639 0 319 136)
						(ego init: posn: 900 120)
						(theGame handsOn:)
					)
				)
			)
			(1105
				(self setRect: 0 0 959 136)
				(ego init: posn: 170 121)
			)
			(1050
				(self setRect: -319 0 639 136)
				(ego init: posn: 566 77)
			)
			(1160
				(self setRect: -319 0 639 136)
				(ego init: posn: 420 125)
			)
			(1190
				(self setRect: -319 0 639 136)
				(ego init: posn: 380 109)
			)
			(1170
				(self setRect: -319 0 639 136)
				(ego init: posn: 597 92)
			)
			(1210
				(self setRect: -639 0 319 136)
				(ego init: posn: 883 105)
			)
			(else 
				(self setRect: -639 0 319 136)
				(ego x: 800 y: 105 view: 800 init: normalize:)
			)
		)
		(UpdatePlane thePlane)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(ratTimer client: 0 dispose: delete:)
		(super dispose:)
	)
	
	(method (cue)
		(super cue:)
		(self setScript: ratGoesAway)
	)
)

(instance getPear of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints {Smack cactus animation.})
				(= ticks 23)
			)
			(1
				(pear init:)
				(theGame handsOn:)
			)
		)
	)
)

(instance head of Feature
	(properties
		noun 8
		nsLeft 40
		nsRight 162
		nsBottom 103
		approachX 170
		approachY 121
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 approachVerbs: 8)
	)
	
	(method (handleEvent event)
		(if (== (user message?) 8) (Load RES_PIC 1110))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 292)
					(if (Btst 13)
						(curRoom newRoom: 1000)
					else
						(curRoom newRoom: 1105)
					)
				else
					(Bset 292)
					(messager say: noun theVerb 13 0)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance prickly of Feature
	(properties
		noun 11
		nsLeft 220
		nsTop 62
		nsRight 254
		nsBottom 90
		approachX 220
		approachY 90
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 11)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(messager say: noun theVerb 17 0)
				)
				(11
					(self setHotspot: 0)
					(curRoom setScript: getPear)
					(return TRUE)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance pear of View
	(properties
		noun 12
		x 210
		y 90
		view 1100
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(ego get: 20)
					(self dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance cave of Feature
	(properties
		nsLeft 493
		nsTop 5
		nsRight 570
		nsBottom 58
		approachX 566
		approachY 77
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 approachVerbs: 8)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(curRoom newRoom: 1050)
					(return TRUE)
				)
			)
		)
	)
)

(instance gourd of Feature
	(properties
		noun 7
		nsLeft 320
		nsTop 48
		nsRight 411
		nsBottom 82
		approachX 370
		approachY 101
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 approachVerbs: 8)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (and (Btst 43) (not (Btst 41)) (not (Btst 42)))
					(messager say: noun theVerb 12 0)
					(Bset 42)
					(ego get: 6)
					(self setHotspot: 0)
				else
					(messager say: noun theVerb 11 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance glyphs of Feature
	(properties
		nsLeft 591
		nsTop 48
		nsRight 640
		nsBottom 61
		approachX 597
		approachY 92
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 9 10 approachVerbs: 9)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(9
					(Prints {You look at the glyphs.})
					(return 1)
				)
			)
		)
	)
)

(instance wetSand of Feature
	(properties
		noun 5
		nsLeft 387
		nsTop 83
		nsRight 432
		nsBottom 93
		approachX 380
		approachY 109
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 8 10 24)
		(if (Btst 293)
			(self setHotspot: 10 24)
		else
			(self setHotspot: 8 10 24)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(24
				(curRoom setScript: plantCornSeed)
			)
			(8
				(Bset 293)
				(super doVerb: theVerb)
			)
		)
	)
)

(instance plantCornSeed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self cue:)
			)
			(1
				(Bset 43)
				(ego put: 14)
				(cornPlant init: setCycle: EndLoop self)
			)
			(2
				(messager say: 5 24 0 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance useHorn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 18 0 1 self)
			)
			(1
				(Prints {You blow the horn in the hole.})
				(self cue:)
			)
			(2
				(jack
					view: 1207
					loop: 0
					cel: 0
					x: 684
					y: 92
					init:
					setCycle: EndLoop self
				)
				(messager say: 4 18 0 2)
			)
			(3
				(ego put: 8)
				(specs
					view: 1207
					loop: 1
					cel: 0
					x: 684
					y: 92
					init:
					setCycle: EndLoop self
				)
				(jack setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4 1)
			(5
				(jack dispose:)
				(hair init:)
				(self cue:)
			)
			(6
				(messager say: 4 18 0 3 self)
				(theGame handsOn:)
			)
		)
	)
)

(instance ratGoesAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ratTimer client: 0 dispose: delete:)
				(Prints {The rat goes back in his hole.})
				(= cycles 1)
			)
			(1
				(rat dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkToHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 1204
					setLoop: 1
					setCel: 0
					setCycle: CycleTo 9 1 self
				)
			)
			(1
				(messager say: 4 8 10 1 self)
			)
			(2
				(jack
					posn: 711 108
					init:
					view: 1204
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(messager sayRange: 4 8 10 2 3 self)
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(4 (jack dispose:) 0)
			(5
				(ego normalize:)
				(messager say: 4 8 10 4 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance knockOnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 8 1 0 self)
			)
			(1
				(rat dispose:)
				(jack init: posn: 717 102 setCycle: EndLoop self)
			)
			(2
				(messager say: 0 0 4 1 self)
				(jack
					posn: 887 94
					setCel: 0
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(3 0)
			(4
				(jack setCel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(5
				(jack setCel: 0 setLoop: 4 setCycle: EndLoop self)
			)
			(6
				(messager say: 0 0 4 2 self)
				(jack setCel: 0 setLoop: 5 setCycle: EndLoop self)
			)
			(7 0)
			(8
				(messager say: 0 0 4 3 self)
				(jack
					posn: 716 100
					setCel: 0
					setLoop: 6
					setCycle: EndLoop self
				)
			)
			(9 0)
			(10
				(Bset 32)
				(jack dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cornPlant of Prop
	(properties
		sightAngle 20
		approachX 380
		approachY 109
		x 395
		y 83
		view 1150
	)
	
	(method (init)
		(super init:)
		(if (not (Btst 16))
			(self setHotspot: 8 10 approachVerbs: 8 10)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(ego get: 19)
				(Bset 16)
				(self setHotspot: 0)
			)
		)
	)
)

(instance rat of View
	(properties
		noun 2
		x 790
		y 50
		view 1450
		cel 1
	)
	
	(method (init)
		(if (and (== global104 -4) (Btst 195))
			(Prints {The rat is afraid of the volcano.})
		else
			(super init: &rest)
			(ratTimer client: self setReal: curRoom 10)
			(self
				setHotspot: 8 10 17 16 5 24 30 28 18 6 12 26 20 22 23 27 15 19 11
			)
		)
	)
	
	(method (dispose)
		(ratTimer client: 0 dispose: delete:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(==
					theVerb
					(OneOf 17 16 30 28 18 6 12 26 20 22 23 27 11 53)
				)
				(Btst 6)
			)
			(messager say: noun theVerb 5 0)
		)
		(switch theVerb
			(8
				(if (Btst 6)
					(messager say: noun theVerb 2 0)
				else
					(messager say: noun theVerb 1 0)
				)
				(self dispose:)
			)
			(19
				(self setHotspot: 0 setHotspot: 0 15)
				(ego put: 9)
				(Bset 6)
				(super doVerb: theVerb)
			)
			(15
				(if (Btst 14)
					(Prints {RAT: I dont need no stinking seed no more.})
					(self dispose:)
				else
					(messager say: noun theVerb 6 0)
					(Bset 14)
					(ego put: 6)
					(ego get: 11)
				)
			)
			(24
				(if (Btst 14)
					(messager say: noun theVerb 7 0)
				else
					(messager say: noun theVerb 6 0)
					(Bset 14)
					(Bset 41)
					(ego put: 14)
					(ego get: 11)
					(self dispose:)
				)
			)
			(58
				(Prints {For a book you get a crook.})
				(ego put: 45 get: 39)
			)
			(5
				(if (Btst 6) (messager say: noun theVerb 8 0))
			)
			(else 
				(if (Btst 6)
					(messager say: noun 0 5 0)
				else
					(super doVerb: theVerb)
				)
			)
		)
		(ratTimer seconds: 10)
	)
)

(instance jack of Actor
	(properties
		x 717
		y 102
		view 1203
	)
)

(instance door of Feature
	(properties
		noun 1
		nsLeft 885
		nsTop 63
		nsRight 905
		nsBottom 85
		approachX 930
		approachY 94
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 8 10)
		(if (not (Btst 14)) (self setHotspot: 8 10))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(rat init:)
				(cond 
					((ego has: 9) (messager say: noun theVerb 2 0))
					((Btst 6) (messager say: noun theVerb 3 0))
					(else (ego setScript: knockOnDoor))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theSign of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(super init:)
		(self
			setHotspot: 8 10
			setPolygon:
				((Polygon new:)
					type: 0
					init: 878 45 880 60 935 54 927 38
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(messager say: noun theVerb 9 0)
				(self setHotspot: 0)
			)
		)
	)
)

(instance hole of Feature
	(properties
		noun 4
		nsLeft 702
		nsTop 96
		nsRight 746
		nsBottom 113
		approachX 750
		approachY 115
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachVerbs: 8 10 18
			setPolygon:
				((Polygon new:)
					type: 0
					init: 687 88 733 104 732 118 700 120 658 99
					yourself:
				)
		)
		(if (not (Btst 7))
			(self setHotspot: 8 10 18)
		else
			(self setHotspot: 8 10)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 38)
					(Prints {You lean over the holes and look cautiously.})
				else
					(Bset 38)
					(ego setScript: talkToHole)
				)
			)
			(18
				(Load RES_VIEW 1207)
				(Bset 7)
				(ego setScript: useHorn)
			)
			(else 
				(Prints {You lean over the holes and look cautiously.})
				(super doVerb: theVerb)
			)
		)
	)
)

(instance hair of View
	(properties
		x 790
		y 100
		view 1450
		cel 4
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
		(self setPri: 400)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(ego get: 10)
				(self dispose:)
				(return 1)
			else
				0
			)
		)
	)
)

(instance specs of Prop
	(properties
		x 790
		y 130
		view 1450
		cel 5
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
		(self setPri: 400)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(ego get: 9)
				(self dispose:)
				(return 1)
			else
				0
			)
		)
	)
)

(instance eSouth of ExitFeature
	(properties
		nsTop 131
		nsRight 950
		nsBottom 136
		exitDir 3
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
)

(instance eWest of ExitFeature
	(properties
		nsRight 20
		nsBottom 140
		exitDir 4
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(if (and (& (event type?) walkEvent) (self onMe: event))
			(= global177 0)
			(= global176 0)
			(curRoom newRoom: 1500)
		else
			(super handleEvent: event)
		)
	)
)

(instance eEast of ExitFeature
	(properties
		nsLeft 939
		nsRight 959
		nsBottom 140
		exitDir 2
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(if (and (& (event type?) walkEvent) (self onMe: event))
			(= global177 4)
			(= global176 0)
			(curRoom newRoom: 1500)
		else
			(super handleEvent: event)
		)
	)
)

(instance ratTimer of Timer)
