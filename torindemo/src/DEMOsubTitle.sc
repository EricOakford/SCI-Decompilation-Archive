;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20100)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use GenDialog)
(use Script)
(use CueMe)
(use ScrollView)
(use Events)
(use ExitFeature)
(use Print)
(use Talker)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	roCliffScroller 0
)

(local
	local0
	local1 =  1
	local2
	local3 =  1
	local4 =  10
	local5
	local6
	local7 =  1
	local8
	local9 =  1
)
(procedure (localproc_0aa3)
	(= local1 1)
	(ego setScaler: Scaler 60 60 1 0)
	(ego setPri: 45)
	(foStartingLedgeToTopLedge init:)
	(foStartingLedgeToBitternut init:)
	(foStartingLedgeToClotheslineLedge init:)
	(= local0
		((Polygon new:)
			type: 3
			init: 82 465 180 465 206 470 206 468 180 463 82 463
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_0b60)
	(if (== local1 1)
		(foStartingLedgeToTopLedge dispose:)
		(foStartingLedgeToBitternut dispose:)
		(foStartingLedgeToClotheslineLedge dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_0f8d)
	(= local1 2)
	(foTopLedgeToStartingLedge init:)
	(if (not ((ScriptID 64017 0) test: 43))
		(foStepTile init:)
	)
	(ego setPri: 25)
	(= local0
		((Polygon new:)
			type: 3
			init: 62 316 62 319 102 319 102 316
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_1031)
	(if (== local1 2)
		(foTopLedgeToStartingLedge dispose:)
		(foStepTile dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_2002)
	(= local1 3)
	(foBridgeDoor init:)
	(foClotheslineLedgeToStartingLedge init:)
	(foClotheslineLedgeToDragon init:)
	(foVeder init:)
	(voSplash init:)
	(if (not ((ScriptID 64017 0) test: 74))
		(foBasket init:)
	)
	(if (not ((ScriptID 64017 0) test: 51))
		(foClothesline init:)
	)
	(ego setScaler: Scaler 70 50 921 720)
	(ego setPri: 65)
	(= local0
		((Polygon new:)
			type: 3
			init:
				0
				720
				0
				899
				158
				899
				218
				888
				189
				822
				215
				812
				148
				785
				128
				785
				107
				780
				98
				763
				111
				753
				140
				753
				175
				775
				198
				775
				222
				765
				188
				765
				80
				722
			yourself:
		)
	)
	(= local5
		((Polygon new:)
			type: 2
			init: 9 750 11 730 53 730 54 748
			yourself:
		)
	)
	(= local6
		((Polygon new:)
			type: 0
			init:
				-3
				808
				13
				808
				0
				803
				0
				798
				22
				795
				76
				795
				97
				792
				43
				785
				24
				777
				24
				773
				40
				766
				18
				761
				31
				753
				131
				740
				139
				742
				79
				755
				90
				765
				57
				772
				102
				785
				131
				794
				121
				800
				39
				809
				39
				815
				62
				821
				-2
				825
			yourself:
		)
	)
	(theDoits add: oSplashTest)
	(curRoom addObstacle: local0 local5)
	(if (not ((ScriptID 64017 0) test: 76))
		(ego setScript: soBoogleGetsWashed)
		(oCliffScrollPlane sitNSpin: 0 587 soBoogleGetsWashed 1)
	else
		(oCliffScrollPlane sitNSpin: 0 587 0 1)
	)
)

(procedure (localproc_22c6)
	(if (== local1 3)
		(foBasket dispose:)
		(foVeder dispose:)
		(foClothesline dispose:)
		(foBridgeDoor dispose:)
		(voSplash dispose:)
		(foClotheslineLedgeToStartingLedge dispose:)
		(foClotheslineLedgeToDragon dispose:)
		((curRoom obstacles?) delete: local0)
		((curRoom obstacles?) delete: local5)
		(local0 dispose:)
		(local5 dispose:)
		(local6 dispose:)
		(theDoits delete: oSplashTest)
	)
	(= local1 0)
)

(procedure (localproc_2608)
	(= local1 4)
	(foPalaceDoor init:)
	(foBridgeToClotheslineLedge init:)
	(ego setScaler: Scaler 33 33 1 0)
	(ego setPri: 35)
	(= local0
		((Polygon new:)
			type: 3
			init: 244 519 578 519 578 516 244 516
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_26ae)
	(if (== local1 4)
		(foPalaceDoor dispose:)
		(foBridgeToClotheslineLedge dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_35e3)
	(= local1 5)
	(ego setScaler: Scaler 60 60 1 0)
	(ego setPri: 85)
	(foDragonToClotheslineLedge init:)
	(foDragonCave init:)
	(foDragonToVultures init:)
	(foRockOverVultures init:)
	(= local0
		((Polygon new:)
			type: 3
			init: 0 1262 0 1277 32 1274 126 1280 128 1262
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_36a3)
	(if (== local1 5)
		(foDragonToClotheslineLedge dispose:)
		(foDragonCave dispose:)
		(foDragonToVultures dispose:)
		(foRockOverVultures dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_4de1)
	(= local1 6)
	(ego setScaler: Scaler 60 60 1 0)
	(ego setPri: 95)
	(foVulturesToDragon init:)
	(if (voTree loop?)
		(voTree setSpeedFraction: (ScriptID 64006 8))
	else
		(voTree setVisibleRange: 36)
	)
	(if ((ScriptID 64017 0) test: 73)
		(foVulturesToLeap init:)
		(= local0
			((Polygon new:)
				type: 3
				init:
					0
					1533
					27
					1586
					189
					1552
					233
					1570
					197
					1545
					267
					1532
					267
					1527
					54
					1544
					48
					1533
				yourself:
			)
		)
	else
		(foVultures init:)
		(foWalkByVultures init:)
		(= local0
			((Polygon new:)
				type: 3
				init: 0 1533 -3 1571 29 1586 144 1558 143 1542 59 1545 45 1532
				yourself:
			)
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_4f68)
	(if (== local1 6)
		(theMusic fade: 0 10 9 1)
		(foVulturesToDragon dispose:)
		(voTree setSpeedFraction: 0)
		(voTree setTotalWidth: 36)
		(foVulturesToLeap dispose:)
		(foWalkByVultures dispose:)
		(foVultures dispose:)
		(foVulturesForward dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_54d6)
	(= local1 7)
	(foLeapLedgeToVultures init:)
	(foLeapAcrossRight init:)
	(= local0
		((Polygon new:)
			type: 3
			init: 290 1339 290 1342 354 1342 354 1339
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_5559)
	(if (== local1 7)
		(foLeapLedgeToVultures dispose:)
		(foLeapAcrossRight dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(procedure (localproc_5b1b)
	(= local1 8)
	(ego setScaler: Scaler 46 46 1 0)
	(ego setPri: 85)
	(foClimbToSeraglio init:)
	(foLeapAcrossLeft init:)
	(foSkunkCave init:)
	(= local0
		((Polygon new:)
			type: 3
			init: 523 1295 489 1297 628 1296 628 1293
			yourself:
		)
	)
	(curRoom addObstacle: local0)
)

(procedure (localproc_5bce)
	(if (== local1 8)
		(foClimbToSeraglio dispose:)
		(foSkunkCave dispose:)
		(foLeapAcrossLeft dispose:)
		((curRoom obstacles?) delete: local0)
		(local0 dispose:)
	)
	(= local1 0)
)

(instance foStartingLedgeToBitternut of Feature
	(properties
		nsTop 446
		nsRight 82
		nsBottom 525
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(ego setScript: soStartingLedgeToBitternut)
	)
)

(instance soStartingLedgeToBitternut of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 88 463 self)
			)
			(1
				(theGame handsOff:)
				(localproc_0b60)
				(= oMessageList 1)
				(self posnList:)
				(ego setHeading: 315 self)
			)
			(2
				(ego setLoop: 7 1)
				(ego setMotion: MoveTo 51 514 self)
			)
			(3
				(ego setLoop: -1)
				(ego setMotion: MoveTo 18 518 self)
			)
			(4 (ego setHeading: 0 self))
			(5 (curRoom newRoom: 20200))
		)
	)
	
	(method (rememberMessage)
		(ego setLoop: -1)
		(curRoom newRoom: 20200)
	)
)

(instance foStartingLedgeToClotheslineLedge of Feature
	(properties
		nsLeft 194
		nsTop 420
		nsRight 230
		nsBottom 511
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(ego setScript: soStartingLedgeToClotheslineLedge)
	)
)

(instance soStartingLedgeToClotheslineLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 200 468 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(localproc_0b60)
				(= oMessageList 1)
				(self posnList:)
				(ego setLoop: 3 1)
				(ego setScaler: Scaler 50 60 480 468)
				(= cycles 2)
			)
			(3
				(ego setMotion: MoveTo 200 480 self)
			)
			(4
				(ego setLoop: -1)
				(ego setMotion: DPath 210 532 134 578 self)
			)
			(5
				(ego setScaler: Scaler 50 50 1 0)
				(= cycles 2)
			)
			(6
				(ego setMotion: DPath 72 600 34 609 -32 662 self)
			)
			(7
				(ego setMotion: MoveTo 18 724 self)
			)
			(8 (ego setHeading: 180 self))
			(9
				(theGame handsOn:)
				(localproc_2002)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego oPanner: 1 -5436 2 posn: 18 724 setMotion: 0)
		(theGame handsOn:)
		(localproc_2002)
		(self dispose:)
	)
)

(instance foStartingLedgeToTopLedge of Feature
	(properties
		nsLeft 118
		nsTop 372
		nsRight 184
		nsBottom 456
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
	)
	
	(method (doVerb)
		(ego setScript: soStartingLedgeToTopLedge)
	)
)

(instance soStartingLedgeToTopLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 140 463 self)
			)
			(1
				(theGame handsOff:)
				(localproc_0b60)
				(= oMessageList 1)
				(self posnList:)
				(ego setScaler: Scaler 60 40 463 374)
				(= cycles 2)
			)
			(2
				(ego setMotion: MoveTo 172 420 self)
			)
			(3
				(ego setPri: 25)
				(ego setMotion: MoveTo 144 374 self)
			)
			(4
				(ego setScaler: Scaler 40 62 374 316)
				(= cycles 2)
			)
			(5 (ego setHeading: 225 self))
			(6
				(ego setLoop: 5 1)
				(ego setMotion: MoveTo 96 316 self)
			)
			(7
				(ego setLoop: 2 0)
				(localproc_0f8d)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 2
			posn: 96 316
			setScaler: Scaler 40 62 374 316
			setPri: 25
			setMotion: 0
		)
		(localproc_0f8d)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance foTopLedgeToStartingLedge of Feature
	(properties
		nsLeft 108
		nsTop 309
		nsRight 184
		nsBottom 412
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(ego setScript: soTopLedgeToStartingLedge)
	)
)

(instance foStepTile of Feature
	(properties
		nsLeft 90
		nsTop 326
		nsRight 104
		nsBottom 340
		approachX 96
		approachY 316
		x 96
		y 321
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self approachVerbs: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTorinGrabsTile)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorinGrabsTile of Prop
	(properties
		x 72
		y 317
		view 20100
		loop 1
	)
)

(instance soTorinGrabsTile of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 72 317 self)
			)
			(1
				(ego hide:)
				(poTorinGrabsTile setCel: 0 init: setCycle: End self)
			)
			(2
				(poTileFalls
					posn: 98 321
					setCel: 0
					init:
					setCycle: CT 2 1 self
				)
			)
			(3 (curRoom newRoom: 21000))
		)
	)
)

(instance soTopLedgeToStartingLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 96 316 self)
			)
			(1 (ego setHeading: 45 self))
			(2
				(theGame handsOff:)
				(localproc_1031)
				(= oMessageList 1)
				(self posnList:)
				(ego setScaler: Scaler 40 62 374 316)
				(= cycles 2)
			)
			(3
				(ego setLoop: 6 1)
				(ego setMotion: MoveTo 144 374 self)
			)
			(4
				(ego setLoop: -1)
				(ego setScaler: Scaler 60 40 463 374)
				(= cycles 2)
			)
			(5
				(ego setMotion: MoveTo 172 420 self)
			)
			(6
				(ego setPri: 45)
				(ego setMotion: MoveTo 140 463 self)
			)
			(7
				(localproc_0aa3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 5
			posn: 140 463
			setPri: 45
			setMotion: 0
		)
		(localproc_0aa3)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance foBridgeDoor of Feature
	(properties
		nsLeft 54
		nsTop 640
		nsRight 114
		nsBottom 720
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(ego setScript: soBridgeDoor)
	)
)

(instance soBridgeDoor of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local7
					(self cue:)
				else
					(= local7 1)
					(self setScript: (ScriptID 64018 1) self)
				)
			)
			(1
				(ego setMotion: PolyPath 74 724 self)
			)
			(2
				(theGame handsOff:)
				(localproc_22c6)
				(= oMessageList 1)
				(self posnList:)
				(ego setPri: 35)
				(ego setMotion: DPath 196 652 134 602 self)
			)
			(3
				(ego setScaler: Scaler 33 33 1 0)
				(ego setMotion: MoveTo 160 516 self)
			)
			(4
				(ego setMotion: MoveTo 300 516 self)
			)
			(5
				(localproc_2608)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 0
			setPri: 35
			setScaler: Scaler 33 33 1 0
			posn: 300 516
			setMotion: 0
		)
		(localproc_2608)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance foClotheslineLedgeToStartingLedge of Feature
	(properties
		nsTop 624
		nsRight 50
		nsBottom 720
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(ego setScript: soClotheslineLedgeToStartingLedge)
	)
)

(instance soClotheslineLedgeToStartingLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local7
					(self cue:)
				else
					(= local7 1)
					(self setScript: (ScriptID 64018 1) self)
				)
			)
			(1
				(ego setMotion: PolyPath 18 724 self)
			)
			(2
				(theGame handsOff:)
				(localproc_22c6)
				(= oMessageList 1)
				(self posnList:)
				(ego setPri: 45)
				(ego setMotion: MoveTo -32 662 self)
			)
			(3
				(ego setMotion: DPath 34 609 72 600 134 578 self)
			)
			(4
				(ego setScaler: Scaler 50 60 480 468)
				(= cycles 2)
			)
			(5
				(ego setMotion: DPath 210 532 200 480 self)
			)
			(6
				(ego setLoop: 2 1)
				(ego setMotion: MoveTo 200 468 self)
			)
			(7
				(ego setLoop: 2 0)
				(localproc_0aa3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 2
			setPri: 45
			setScaler: Scaler 60 60 1 0
			posn: 200 468
			setMotion: 0
		)
		(localproc_0aa3)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance foClotheslineLedgeToDragon of Feature
	(properties
		nsLeft 206
		nsTop 832
		nsRight 284
		nsBottom 938
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(ego setScript: soClotheslineLedgeToDragon)
	)
)

(instance soClotheslineLedgeToDragon of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local7
					(self cue:)
				else
					(= local7 1)
					(self setScript: (ScriptID 64018 1) self)
				)
			)
			(1
				(ego setMotion: PolyPath 204 816 self)
			)
			(2
				(theGame handsOff:)
				(localproc_22c6)
				(= oMessageList 1)
				(self posnList:)
				(ego setScaler: Scaler 60 60 1 0)
				(ego setPri: 55)
				(ego
					setMotion: DPath 272 926 240 1000 150 1047 202 1154 246 1195 self
				)
			)
			(3
				(ego setPri: 85)
				(ego setMotion: MoveTo 126 1280 self)
			)
			(4
				(theGame handsOn:)
				(localproc_35e3)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 5
			setPri: 85
			setScaler: Scaler 60 60 1 0
			posn: 126 1280
			setMotion: 0
		)
		(theGame handsOn:)
		(localproc_35e3)
		(self dispose:)
	)
)

(instance voClothespin of View
	(properties
		x 68
		y 848
		view 20117
	)
)

(instance foClothesline of Feature
	(properties
		nsTop 825
		nsRight 122
		nsBottom 864
		approachX 48
		approachY 914
		x 48
		y 912
	)
	
	(method (init)
		(super init: &rest)
		(if (not ((ScriptID 64017 0) test: 51))
			(self setVisibleRange: 1)
		)
		(self approachVerbs: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego get: ((ScriptID 64001 0) get: 23))
				((ScriptID 64017 0) set: 51)
				(voClothespin dispose:)
				(self setTotalWidth: 1)
				(messager say: 6 1 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foBasket of Feature
	(properties
		nsLeft 100
		nsTop 751
		nsRight 173
		nsBottom 780
		approachX 186
		approachY 771
		x 185
		y 772
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 29)
		(self approachVerbs: 2 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 9 1 12 0))
			(29 (ego setScript: soWashTile))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorinAndTile of Prop
	(properties
		x 186
		y 771
		view 20107
		loop 3
	)
)

(instance soWashTile of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(poTorinAndTile
					setLoop: 3
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(1
				(ego put: ((ScriptID 64001 0) get: 19))
				((ScriptID 64017 0) set: 74)
				(= ticks
					(*
						2
						(+ 1 (poWasherLady lastCel:))
						(poWasherLady cycleSpeed?)
					)
				)
			)
			(2
				(poTorinAndTile setLoop: 4 setCel: 0 setCycle: End self)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 33))
				((ScriptID 64017 0) set: 60)
				(poTorinAndTile dispose:)
				(foBasket dispose:)
				(ego show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poWasherLady of Prop
	(properties
		x 149
		y 777
		view 20107
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
		(self setPri: 65)
	)
)

(instance poBoogleGetsWashed of Prop
	(properties
		view 20107
	)
)

(instance soBoogleGetsWashed of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 64017 0) set: 76)
				(= local7 0)
			)
			(1
				(self setScript: (ScriptID 64018 2) self)
			)
			(2
				((ScriptID 64018 0)
					bSwing: 0
					setPri: 65
					show:
					setMotion: PolyPath 188 770 self
				)
			)
			(3
				((ScriptID 64018 0) setHeading: 270 self)
			)
			(4
				((ScriptID 64018 0) hide:)
				(poBoogleGetsWashed
					setLoop: 1
					setCel: 0
					setPri: (- (poWasherLady priority?) 1)
					posn: 192 773
					init:
					setCycle: End self
				)
			)
			(5
				(poWasherLady setCycle: End self)
			)
			(6
				(poBoogleGetsWashed
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
				(poWasherLady setCel: 0 setCycle: End self)
			)
			(7)
			(8
				(poWasherLady setCycle: Fwd)
				(poBoogleGetsWashed
					setLoop: 5
					setCel: 0
					posn: 233 770
					setCycle: End self
				)
			)
			(9
				(poBoogleGetsWashed dispose:)
				((ScriptID 64018 0)
					posn: 224 770
					show:
					setMotion: MoveTo 188 770 self
				)
			)
			(10
				((ScriptID 64018 0) bSwing: 1)
				(theGame handsOn:)
			)
		)
	)
)

(instance foVeder of Feature
	(properties
		nsLeft 419
		nsTop 865
		nsRight 442
		nsBottom 878
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: soYellAtVeder)
	)
)

(instance poTorinYells of Prop
	(properties
		x 212
		y 888
		view 20102
	)
	
	(method (init)
		(super init: &rest)
		(self nCurPosY: 128)
	)
)

(instance soYellAtVeder of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 212 888 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(ego hide:)
				(poTorinYells setCel: 0 init: setCycle: CT 20 1 self)
			)
			(3
				(messager say: 3 1 13 0 self)
			)
			(4
				(poTorinYells setCycle: End self)
				(poVeder setCycle: End)
			)
			(5
				(poTorinYells dispose:)
				(ego show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oSplashTest of Code
	(properties)
	
	(method (doit)
		(if
		(and (ego mover?) (local6 onMe: (ego x?) (ego y?)))
			(voSplash
				posn: (ego x?) (ego y?)
				loop: (ego loop?)
				cel: (ego cel:)
				nCurPosX: (ego scaleX?) (ego scaleY?)
				show:
			)
		else
			(voSplash hide:)
		)
	)
)

(instance voSplash of View
	(properties
		view -5425
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 66 1)
	)
)

(instance foPalaceDoor of ExitFeature
	(properties
		nsLeft 560
		nsTop 468
		nsRight 598
		nsBottom 516
		approachX 576
		approachY 516
		x 576
		y 480
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(localproc_26ae)
		(curRoom newRoom: 20300)
	)
)

(instance foBridgeToClotheslineLedge of Feature
	(properties
		nsLeft 180
		nsTop 458
		nsRight 278
		nsBottom 516
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
	)
	
	(method (doVerb)
		(ego setScript: soBridgeToClotheslineLedge)
	)
)

(instance soBridgeToClotheslineLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 244 516 self)
			)
			(1
				(theGame handsOff:)
				(localproc_26ae)
				(= oMessageList 1)
				(self posnList:)
				(ego setMotion: DPath 160 516 134 602 self)
			)
			(2
				(= cycles 2)
				(ego setScaler: Scaler 50 50 1 0)
			)
			(3
				(ego setMotion: DPath 196 652 74 724 self)
			)
			(4
				(theGame handsOn:)
				(localproc_2002)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 5
			setScaler: Scaler 50 50 1 0
			posn: 74 724
			setMotion: 0
		)
		(theGame handsOn:)
		(localproc_2002)
		(self dispose:)
	)
)

(instance foDragonToClotheslineLedge of Feature
	(properties
		nsLeft 114
		nsTop 1195
		nsRight 244
		nsBottom 1286
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(if (= local8 (MakeMessageText 14 0 0 1 300))
			(TextDialog local8 continueText)
			(local8 dispose:)
			(= local8 0)
		)
	)
)

(instance soDragonToClotheslineLedge of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 126 1280 self)
			)
			(1
				(theGame handsOff:)
				(localproc_36a3)
				(= oMessageList 1)
				(self posnList:)
				(ego setMotion: MoveTo 246 1195 self)
			)
			(2
				(ego setPri: 55)
				(ego
					setMotion: DPath 202 1154 150 1039 240 1005 272 926 204 816 self
				)
			)
			(3
				(theGame handsOn:)
				(localproc_2002)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 7
			setPri: 55
			posn: 204 816
			setMotion: 0
		)
		(theGame handsOn:)
		(localproc_2002)
		(self dispose:)
	)
)

(instance foDragonCave of ExitFeature
	(properties
		nsLeft 2
		nsTop 1180
		nsRight 92
		nsBottom 1257
		approachX 46
		approachY 1262
		x 44
		y 1260
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
	)
	
	(method (doVerb)
		(ego nSaveTime: self self)
	)
	
	(method (cue)
		(if (= local8 (MakeMessageText 10 0 0 1 300))
			(TextDialog local8 continueText)
			(local8 dispose:)
			(= local8 0)
		)
	)
)

(instance foDragonToVultures of Feature
	(properties
		nsTop 1272
		nsRight 60
		nsBottom 1351
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(ego setScript: soDragonToVultures)
	)
)

(instance soDragonToVultures of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 14 1274 self)
			)
			(1
				(theGame handsOff:)
				(localproc_36a3)
				(= oMessageList 1)
				(self posnList:)
				(ego
					setMotion: DPath -30 1341 -176 1420 -44 1464 38 1543 self
				)
			)
			(2
				(theGame handsOn:)
				(localproc_4de1)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego oPanner: 1 -5436 4 posn: 38 1543 setMotion: 0)
		(theGame handsOn:)
		(localproc_4de1)
		(self dispose:)
	)
)

(instance foRockOverVultures of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 35 1)
		(self nScrollMaxX: 10)
		(self
			createPoly:
				158
				1236
				180
				1237
				208
				1292
				224
				1306
				263
				1315
				288
				1311
				288
				1322
				271
				1338
				253
				1345
				227
				1345
				199
				1335
				166
				1305
				136
				1282
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(35
				(if (not ((ScriptID 64017 0) test: 73))
					(ego setScript: soTorinDropsMeat)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorinPullsOutMeat of Prop
	(properties
		x 122
		y 1275
		view 20104
	)
)

(instance poVulturesFlyAway of Prop
	(properties
		x 289
		y 1587
		view 20104
		loop 5
	)
)

(instance aoFlyingMeat of Actor
	(properties
		view 20104
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(= xStep 3)
		(= yStep 0)
	)
	
	(method (doit)
		(if local3
			(self posn: (+ x xStep) (+ y yStep))
			(if (> y 1650) (self dispose:) (return))
			(if (== local2 0)
				(= local2 2)
				(= yStep (Min (+ yStep 1) local4))
			else
				(-- local2)
			)
		)
		(super doit: &rest)
	)
)

(instance voVultureBodyMeat of View
	(properties
		view 20104
		loop 10
	)
	
	(method (init)
		(= x (poVulturesFlyAway x?))
		(= y (poVulturesFlyAway y?))
		(super init: &rest)
	)
)

(instance poVisceraIdleMeat of Prop
	(properties
		view 20104
		loop 14
	)
	
	(method (init)
		(= x (poVulturesFlyAway x?))
		(= y (poVulturesFlyAway y?))
		(super init: &rest)
		(= cycleSpeed 30)
		(self setCycle: RandCycle)
	)
)

(instance poTripeIdleMeat of Prop
	(properties
		view 20104
		loop 13
	)
	
	(method (init)
		(= x (poVulturesFlyAway x?))
		(= y (poVulturesFlyAway y?))
		(super init: &rest)
		(= cycleSpeed 30)
		(self setCycle: RandCycle)
	)
)

(instance toTripeMeat of Talker
	(properties
		view 20104
		loop 11
		priority 2000
	)
	
	(method (init)
		(= x (poVulturesFlyAway x?))
		(= y (poVulturesFlyAway y?))
		(poVulturesFlyAway hide:)
		(voVultureBodyMeat init:)
		(poVisceraIdleMeat init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(poVulturesFlyAway show:)
		(voVultureBodyMeat dispose:)
		(poVisceraIdleMeat dispose:)
		(super dispose: &rest)
	)
)

(instance toVisceraMeat of Talker
	(properties
		view 20104
		loop 12
		priority 2000
	)
	
	(method (init)
		(= x (poVulturesFlyAway x?))
		(= y (poVulturesFlyAway y?))
		(poVulturesFlyAway hide:)
		(voVultureBodyMeat init:)
		(poTripeIdleMeat init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(poVulturesFlyAway show:)
		(voVultureBodyMeat dispose:)
		(poTripeIdleMeat dispose:)
		(super dispose: &rest)
	)
)

(instance toTorinPullsOutMeat of Talker
	(properties
		x 122
		y 1275
		view 20104
		loop 1
		priority 1276
	)
)

(instance soTorinDropsMeat of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 122 1275 self)
				(oCliffScrollPlane sitNSpin: 0 1072 self 1)
			)
			(1)
			(2 (ego setHeading: 135 self))
			(3
				(ego scrollTo:)
				(= cycles 2)
			)
			(4
				(theSound nHeight: 20108 20198 20121 20110)
				(ego hide:)
				(poTorinPullsOutMeat init: setCycle: End self)
			)
			(5
				(= gToTorinPullsOutMeat toTorinPullsOutMeat)
				(messager say: 2 35 0 1 self)
			)
			(6
				(= gToTorinPullsOutMeat 0)
				(poTorinPullsOutMeat
					setLoop: 2
					setCel: 0
					setCycle: CT 13 1 self
				)
			)
			(7
				(= ticks (poTorinPullsOutMeat cycleSpeed?))
				(theSound lThumbLoop: 20108)
			)
			(8
				(poTorinPullsOutMeat setCycle: End self)
			)
			(9
				(poTorinPullsOutMeat dispose:)
				(ego show:)
				(ego put: ((ScriptID 64001 0) get: 25))
				((ScriptID 64017 0) set: 73)
				(aoFlyingMeat
					setLoop: 3 1
					setCel: 0
					posn: 381 1241
					init:
					setCycle: Fwd
				)
				(poVultures dispose:)
				(poVulturesFlyAway init:)
				(oCliffScrollPlane sitNSpin: 0 1262 self 1)
			)
			(10
				(theMusic pageSize: 20198)
				(poVulturesFlyAway setCycle: CT 30 1 self)
			)
			(11
				(= ticks (poVulturesFlyAway cycleSpeed?))
			)
			(12
				(theSound lThumbLoop: 20121)
				(poVulturesFlyAway setCycle: End self)
			)
			(13
				(= gToVisceraMeat toVisceraMeat)
				(= gToTripeMeat toTripeMeat)
				(messager sayRange: 2 35 0 2 3 self)
			)
			(14
				(poVulturesFlyAway
					setLoop: 6
					setCel: 0
					posn: 266 1570
					setCycle: End self
				)
			)
			(15
				(poVulturesFlyAway
					setLoop: 7
					setCel: 0
					posn: 287 1563
					setCycle: CT 8 1 self
				)
			)
			(16
				(= ticks (poVulturesFlyAway cycleSpeed?))
				(theSound lThumbLoop: 20110)
			)
			(17
				(poVulturesFlyAway setCycle: End self)
			)
			(18
				(poVulturesFlyAway dispose:)
				(= gToVisceraMeat 0)
				(= gToTripeMeat 0)
				(oCliffScrollPlane sitNSpin: 0 1580 self 2)
				(messager sayRange: 2 35 0 4 5 self)
			)
			(19)
			(20
				(oCliffScrollPlane sitNSpin: 0 1072 self 1)
				(theMusic fade: 0 10 9 1)
			)
			(21
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			setMotion: 0
			posn: 122 1275
			setLoop: 4
			scrollTo:
			show:
			put: ((ScriptID 64001 0) get: 25)
		)
		((ScriptID 64017 0) set: 73)
		(oCliffScrollPlane bSpinning: fadeRel: 0 1072)
		(poTorinPullsOutMeat dispose:)
		(aoFlyingMeat dispose:)
		(poVultures dispose:)
		(poVulturesFlyAway dispose:)
		(= gToVisceraMeat 0)
		(= gToTripeMeat 0)
		(theMusic pageSize: 0)
		(theSound stop:)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(ego
			setMotion: 0
			posn: 122 1275
			setLoop: 4
			scrollTo:
			show:
			get: ((ScriptID 64001 0) get: 25)
		)
		((ScriptID 64017 0) unlockAudio: 73)
		(oCliffScrollPlane bSpinning: fadeRel: 0 1072)
		(poTorinPullsOutMeat setLoop: 0 setCel: 0 dispose:)
		(aoFlyingMeat dispose:)
		(poVultures init:)
		(poVulturesFlyAway
			view: 20104
			setLoop: 5
			setCel: 0
			posn: 289 1587
			dispose:
		)
		(= gToVisceraMeat toViscera)
		(= gToTripeMeat toTripe)
		(theMusic pageSize: 0)
		(theSound stop:)
		(ego setScript: self)
	)
)

(instance foVulturesToDragon of Feature
	(properties
		nsTop 1466
		nsRight 72
		nsBottom 1538
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(ego setScript: soVulturesToDragon)
	)
)

(instance soVulturesToDragon of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((ScriptID 64017 0) test: 73))
					(poVultures setCycle: Beg)
					(foVulturesForward dispose:)
					(foVultures init:)
				)
				(ego setMotion: PolyPath 38 1543 self)
			)
			(1
				(theGame handsOff:)
				(localproc_4f68)
				(= oMessageList 1)
				(self posnList:)
				(ego
					setMotion: DPath -44 1464 -176 1420 -30 1341 14 1274 self
				)
			)
			(2
				(theGame handsOn:)
				(localproc_35e3)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego oPanner: 1 -5436 6 posn: 14 1274 setMotion: 0)
		(theGame handsOn:)
		(localproc_35e3)
		(self dispose:)
	)
)

(instance foVulturesToLeap of Feature
	(properties
		nsLeft 216
		nsTop 1462
		nsRight 271
		nsBottom 1530
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(ego setScript: soVulturesToLeap)
	)
)

(instance foWalkByVultures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsTop (foVulturesToLeap nsTop?))
		(= nsBottom (foVulturesToLeap nsBottom?))
		(= nsLeft (foVulturesToLeap nsLeft?))
		(= nsRight (foVulturesToLeap nsRight?))
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(foVultures doVerb: &rest)
	)
)

(instance soVulturesToLeap of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 257 1533 self)
			)
			(1
				(theGame handsOff:)
				(localproc_4f68)
				(ego setPri: 85)
				(= oMessageList 1)
				(self posnList:)
				(ego setMotion: DPath 190 1454 184 1339 self)
			)
			(2
				(ego setScaler: Scaler 46 46 1 0)
				(= cycles 2)
			)
			(3
				(ego setMotion: MoveTo 306 1339 self)
			)
			(4
				(theGame handsOn:)
				(localproc_54d6)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 0
			setScaler: Scaler 46 46 1 0
			setPri: 85
			posn: 306 1339
			setMotion: 0
		)
		(theGame handsOn:)
		(localproc_54d6)
		(self dispose:)
	)
)

(instance poVultures of Prop
	(properties
		x 264
		y 1570
		view 20105
	)
)

(instance poVulturesEatMeat of Prop
	(properties
		x 264
		y 1570
		view 20105
		loop 4
	)
)

(instance foVultures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 35)
		(self
			createPoly: 230 1565 288 1489 335 1494 328 1528 275 1571
		)
	)
	
	(method (doVerb theVerb)
		(theMusic pageSize: 20199)
		(switch theVerb
			(1
				(ego setScript: soTorinTalksToVultures)
			)
			(35
				(ego setScript: soTorinGivesMeat)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foVulturesForward of Feature
	(properties
		nsLeft 144
		nsTop 1465
		nsRight 263
		nsBottom 1563
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 35)
	)
	
	(method (doVerb)
		(foVultures doVerb: &rest)
	)
)

(instance voVultureBody of View
	(properties
		view 20105
		loop 6
	)
	
	(method (init)
		(= x (poVultures x?))
		(= y (poVultures y?))
		(super init: &rest)
	)
)

(instance poVisceraIdle of Prop
	(properties
		view 20105
		loop 10
	)
	
	(method (init)
		(= x (poVultures x?))
		(= y (poVultures y?))
		(super init: &rest)
		(= cycleSpeed 30)
		(self setCycle: RandCycle)
	)
)

(instance poTripeIdle of Prop
	(properties
		view 20105
		loop 9
	)
	
	(method (init)
		(= x (poVultures x?))
		(= y (poVultures y?))
		(super init: &rest)
		(= cycleSpeed 30)
		(self setCycle: RandCycle)
	)
)

(instance toTripe of Talker
	(properties
		view 20105
		loop 7
		priority 2000
	)
	
	(method (init)
		(= x (poVultures x?))
		(= y (poVultures y?))
		(poVultures hide:)
		(voVultureBody init:)
		(poVisceraIdle init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(poVultures show:)
		(voVultureBody dispose:)
		(poVisceraIdle dispose:)
		(super dispose: &rest)
	)
)

(instance toViscera of Talker
	(properties
		view 20105
		loop 8
	)
	
	(method (init)
		(= priority (- (poVultures y?) 5))
		(= x (poVultures x?))
		(= y (poVultures y?))
		(poVultures hide:)
		(voVultureBody init:)
		(poTripeIdle init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(poVultures show:)
		(voVultureBody dispose:)
		(poTripeIdle dispose:)
		(super dispose: &rest)
	)
)

(instance soTorinTalksToVultures of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch global262
					(0 (= global262 7))
					(7 (= global262 8))
					(8 (= global262 9))
					(else 
						(= global262 10)
						((ScriptID 64017 0) set: 75)
					)
				)
				(ego oCuee: poVultures self)
			)
			(1
				(if (> (ego x?) 118)
					(ego setLoop: 0 1)
					(ego setMotion: PolyPath 118 (ego y?) self)
				else
					(self cue:)
				)
			)
			(2
				(ego setLoop: -1)
				(messager say: 4 1 global262 1 self)
				(poVultures setCycle: End self)
				(foVultures dispose:)
				(foVulturesForward init:)
			)
			(3)
			(4
				(switch global262
					(7
						(messager sayRange: 4 1 7 2 9 self)
					)
					(8
						(messager sayRange: 4 1 8 2 9 self)
					)
					(9
						(messager sayRange: 4 1 9 2 4 self)
					)
					(10
						(messager sayRange: 4 1 10 2 4 self)
					)
					(else  (self cue:))
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oChomp of TPSound
	(properties)
)

(instance soTorinGivesMeat of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 119 1550 self)
				(poVultures setCycle: End self)
				(foVultures dispose:)
				(foVulturesForward init:)
			)
			(1)
			(2 (ego setHeading: 135 self))
			(3
				(ego scrollTo:)
				(= cycles 2)
			)
			(4
				(theSound nHeight: 20120 20115)
				(messager say: 4 35 0 1 self)
			)
			(5
				(ego hide:)
				(poTorinOffersMeat
					setLoop: 3
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(6
				(poVultures hide:)
				(poVulturesEatMeat setCel: 0 init: setCycle: CT 18 1 self)
			)
			(7
				(oChomp lThumbLoop: 20120)
				(ego put: ((ScriptID 64001 0) get: 25))
				(= ticks (poVulturesEatMeat cycleSpeed?))
			)
			(8
				(theSound lThumbLoop: 20115)
				(poTorinOffersMeat
					setLoop: 5
					setCel: 0
					setCycle: End self
				)
				(poVulturesEatMeat setCycle: End self)
			)
			(9)
			(10
				(poTorinOffersMeat dispose:)
				(poVulturesEatMeat dispose:)
				(poVultures show:)
				(ego posn: 44 1549 show:)
				(messager sayRange: 4 35 0 2 8 self)
			)
			(11
				(theGame handsOn:)
				(if (== local9 1)
					(if (= local8 (MakeMessageText 17 0 0 local9 300))
						(TextDialog local8 continueText)
						(local8 dispose:)
						(= local8 0)
					)
					(= local9 (Min 4 (+ local9 1)))
				)
				(if (= local8 (MakeMessageText 17 0 0 local9 300))
					(TextDialog local8 continueText)
					(local8 dispose:)
					(= local8 0)
				)
				(= local9 (Min 4 (+ local9 1)))
				(ego get: ((ScriptID 64001 0) get: 25))
				(self dispose:)
			)
		)
	)
)

(instance poTorinOffersMeat of Prop
	(properties
		x 100
		y 1555
		view 20105
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: (ego priority?))
	)
)

(class DEMOsubTitle of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		curSound 0
		initted 0
	)
	
	(method (dispose)
		(if curSound (curSound dispose:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (= local8 (MakeMessageText 20 0 11 1 300))
					(TextDialog local8 continueText)
					(local8 dispose:)
				)
				(if ((ScriptID 64002 10) slotIncX?)
					(= initted 1)
					((ScriptID 64002 10) slotIncY: 0)
					(self setScript: (ScriptID 64002 12) self)
				else
					(= initted 0)
					(self cue:)
				)
			)
			(1
				(= curSound (MakeMessageSubtitle curRoomNum 5 1 3 1))
				(= seconds 4)
			)
			(2
				(if curSound (curSound dispose:) (= curSound 0))
				(if (== initted 1)
					((ScriptID 64002 10) slotIncY: 1)
					(self setScript: (ScriptID 64002 11) self)
				else
					(self cue:)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance voTree of View
	(properties
		x 192
		y 1887
		view 20120
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(if (not ((ScriptID 64017 0) test: 72))
			(self setVisibleRange: 1)
		)
	)
	
	(method (doVerb theVerb)
		(if loop
			(if ((ScriptID 64017 0) test: 73)
				(ego setScript: soBigTree)
			else
				(ego setScript: soTorinTalksToVultures)
			)
		else
			(switch theVerb
				(1
					(ego setScript: DEMOsubTitle)
				)
				(36
					(ego setScript: soTorinFertilizesTree)
				)
				(else  (super doVerb: &rest))
			)
		)
	)
)

(instance poTorinFertilizesTree of Prop
	(properties
		x 89
		y 1571
		view 20106
	)
)

(instance poTreeGrows of Prop
	(properties
		x 192
		y 1887
		view 20121
	)
)

(instance soTorinFertilizesTree of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theSound nHeight: 20119 20112)
				(ego setMotion: PolyPath 89 1571 self)
				(oCliffScrollPlane sitNSpin: 0 1424 self 1)
			)
			(1)
			(2 (ego setHeading: 135 self))
			(3
				(messager say: 5 36 3 0)
				(= seconds 3)
			)
			(4
				(ego hide:)
				(poTorinFertilizesTree
					setCel: 0
					init:
					setCycle: CT 20 1 self
				)
			)
			(5
				(theSound lThumbLoop: 20119)
				(poTorinFertilizesTree setCycle: End self)
			)
			(6
				(ego put: ((ScriptID 64001 0) get: 26))
				((ScriptID 64017 0) set: 72)
				(oCliffScrollPlane sitNSpin: 0 1580 self 1)
			)
			(7
				(voTree dispose:)
				(theSound lThumbLoop: 20112)
				(poTreeGrows setCel: 0 init: setCycle: End self)
			)
			(8
				(poTreeGrows dispose:)
				(voTree setLoop: 1 init:)
				(voTree setSpeedFraction: (ScriptID 64006 8))
				(voTree setTotalWidth: 1 36)
				(oCliffScrollPlane sitNSpin: 0 1424 self 1)
			)
			(9
				(poTorinFertilizesTree
					setLoop: 3
					setCel: 0
					setCycle: End self
				)
			)
			(10
				(poTorinFertilizesTree dispose:)
				(ego show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			setMotion: 0
			posn: 89 1571
			loop: 4
			scrollTo:
			show:
			put: ((ScriptID 64001 0) get: 26)
		)
		((ScriptID 64017 0) set: 72)
		(poTorinFertilizesTree dispose:)
		(poTreeGrows dispose:)
		(theSound stop:)
		(oCliffScrollPlane bSpinning:)
		(oCliffScrollPlane fadeRel: 0 1424)
		(voTree setLoop: 1 init:)
		(voTree setSpeedFraction: (ScriptID 64006 8))
		(voTree setTotalWidth: 1 36)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(ego
			setMotion: 0
			posn: 89 1571
			loop: 4
			scrollTo:
			show:
			get: ((ScriptID 64001 0) get: 26)
		)
		(poTorinFertilizesTree
			view: 20106
			setLoop: 0
			setCel: 0
			posn: 89 1571
			dispose:
		)
		(poTreeGrows dispose:)
		(voTree setLoop: 0 init:)
		(voTree setSpeedFraction: 0)
		((ScriptID 64017 0) unlockAudio: 72)
		(theSound stop:)
		(oCliffScrollPlane bSpinning:)
		(oCliffScrollPlane fadeRel: 0 1424)
		(ego setScript: self)
	)
)

(instance poTorinClimbsDownTree of Prop
	(properties
		x 310
		y 1560
		view 20109
	)
)

(instance soBigTree of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 310 1560 self)
			)
			(1
				(theGame handsOff:)
				(localproc_5559)
				(= oMessageList 1)
				(self posnList:)
				(ego setMotion: MoveTo 310 1560 self)
			)
			(2
				(oCliffScrollPlane sitNSpin: 0 1440 self 1)
				(ego setHeading: 135 self)
			)
			(3)
			(4
				(ego scrollTo:)
				(= cycles 2)
			)
			(5
				(theSound nHeight: 20116)
				(ego hide:)
				(poTorinClimbsDownTree
					setCel: 0
					init:
					setPri: 2000
					setCycle: CT 16 1 self
				)
			)
			(6
				(theSound lThumbLoop: 20116)
				(oCliffScrollPlane sitNSpin: 0 1580 self 1)
				(poTorinClimbsDownTree setCycle: End self)
			)
			(7)
			(8 (curRoom newRoom: 20700))
		)
	)
	
	(method (rememberMessage)
		(theSound stop:)
		(oCliffScrollPlane bSpinning:)
		(curRoom newRoom: 20700)
	)
)

(instance foLeapLedgeToVultures of Feature
	(properties
		nsLeft 226
		nsTop 1305
		nsRight 292
		nsBottom 1344
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
	)
	
	(method (doVerb)
		(ego setScript: soLeapLedgeToVultures)
	)
)

(instance soLeapLedgeToVultures of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 306 1351 self)
			)
			(1
				(theGame handsOff:)
				(localproc_5559)
				(= oMessageList 1)
				(self posnList:)
				(ego setMotion: MoveTo 184 1339 self)
			)
			(2
				(ego hide:)
				(ego setScaler: Scaler 60 60 1 0)
				(= cycles 2)
			)
			(3
				(ego setMotion: MoveTo 195 1454 self)
			)
			(4
				(ego show:)
				(ego setMotion: MoveTo 257 1533 self)
			)
			(5
				(theGame handsOn:)
				(localproc_4de1)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(ego
			oPanner: 1 -5436 4
			posn: 257 1533
			setScaler: Scaler 60 60 1 0
			show:
			setMotion: 0
		)
		(theGame handsOn:)
		(localproc_4de1)
		(self dispose:)
	)
)

(instance foLeapAcrossRight of Feature
	(properties
		nsLeft 390
		nsTop 1289
		nsRight 508
		nsBottom 1377
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(ego setScript: soLeapAcrossRight)
	)
)

(instance poTorinLeapsRight of Prop
	(properties
		x 293
		y 1339
		view 20112
	)
)

(instance soLeapAcrossRight of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 293 1339 self)
				(oCliffScrollPlane sitNSpin: 0 1183)
			)
			(1 (ego setHeading: 90 self))
			(2
				(theGame handsOff:)
				(localproc_5559)
				(ego scrollTo:)
				(= cycles 2)
			)
			(3
				(= oMessageList 1)
				(self posnList:)
				(theSound nHeight: 20113)
				(messager say: 7 1 2 1 self)
			)
			(4
				(ego hide:)
				(theSound lThumbLoop: 20113)
				(poTorinLeapsRight setCel: 0 init: setCycle: End self)
			)
			(5
				(poTorinLeapsRight dispose:)
				(ego posn: 478 1294 show: setMotion: MoveTo 489 1297 self)
			)
			(6
				(theGame handsOn:)
				(localproc_5b1b)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(theSound stop:)
		(poTorinLeapsRight dispose:)
		(ego oPanner: 1 -5436 0 posn: 489 1297 show: setMotion: 0)
		(theGame handsOn:)
		(localproc_5b1b)
		(self dispose:)
	)
)

(instance foLeapAcrossLeft of Feature
	(properties
		nsLeft 325
		nsTop 1291
		nsRight 466
		nsBottom 1377
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(ego setScript: soLeapAcrossLeft)
	)
)

(instance poTorinLeapsLeft of Prop
	(properties
		x 564
		y 1293
		view 20112
		loop 1
	)
)

(instance soLeapAcrossLeft of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 564 1293 self)
			)
			(1 (ego setHeading: 225 self))
			(2
				(theGame handsOff:)
				(localproc_5bce)
				(ego scrollTo:)
				(= cycles 2)
			)
			(3
				(= oMessageList 1)
				(self posnList:)
				(theSound nHeight: 20114)
				(ego hide:)
				(theSound lThumbLoop: 20114)
				(poTorinLeapsLeft
					setCel: 0
					init:
					setPri: 89
					setCycle: End self
				)
			)
			(4
				(poTorinLeapsLeft dispose:)
				(ego posn: 310 1342 setLoop: 1 scrollTo: show:)
				(theGame handsOn:)
				(localproc_54d6)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(theSound stop:)
		(poTorinLeapsLeft dispose:)
		(ego oPanner: 1 -5436 0 posn: 310 1342 show: setMotion: 0)
		(theGame handsOn:)
		(localproc_54d6)
		(self dispose:)
	)
)

(instance foSkunkCave of Feature
	(properties
		nsLeft 548
		nsTop 1200
		nsRight 630
		nsBottom 1291
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(if (= local8 (MakeMessageText 11 0 0 1 300))
			(TextDialog local8 continueText)
			(local8 dispose:)
			(= local8 0)
		)
	)
)

(instance soSkunkCave of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 582 1293 self)
			)
			(1
				(theGame handsOff:)
				(localproc_5bce)
				(ego setMotion: MoveTo 612 1278 self)
			)
			(2 (curRoom newRoom: 20500))
		)
	)
)

(instance foClimbToSeraglio of Feature
	(properties
		nsLeft 538
		nsTop 1293
		nsRight 628
		nsBottom 1384
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 7))
	)
	
	(method (doVerb)
		(if (= local8 (MakeMessageText 12 0 0 1 300))
			(TextDialog local8 continueText)
			(local8 dispose:)
			(= local8 0)
		)
	)
)

(instance poTorinClimbsToSeraglio of Prop
	(properties
		x 548
		y 1293
		view 20114
	)
)

(instance soClimbToSeraglio of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 548 1293 self)
			)
			(1 (ego setHeading: 125 self))
			(2
				(if ((ScriptID 64017 0) test: 79)
					(self cue:)
				else
					((ScriptID 64017 0) set: 79)
					(messager say: 8 1 4 0 self)
				)
			)
			(3
				(theGame handsOff:)
				(localproc_5bce)
				(ego hide:)
				(= oMessageList 1)
				(self posnList:)
				(poTorinClimbsToSeraglio
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(4 (curRoom newRoom: 20600))
		)
	)
	
	(method (rememberMessage)
		(curRoom newRoom: 20600)
	)
)

(instance poTorinFallsIn of Prop
	(properties
		x 109
		y 117
		view 20100
	)
)

(instance poTileFalls of Prop
	(properties
		view 20100
		loop 2
	)
)

(instance oThud of TPSound
	(properties)
)

(instance soEgoFallsIn of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego dispose:)
				(oCliffScrollPlane fadeRel: 0 0)
				(theSound nHeight: 20102 20105 20106)
				(theSound lThumbLoop: 20101 self)
			)
			(1
				(poTorinFallsIn setCel: 0 init: setCycle: CT 13 1 self)
			)
			(2
				(theSound lThumbLoop: 20102 self)
			)
			(3
				(oCliffScrollPlane sitNSpin: 0 156 self 1)
				(theSound lThumbLoop: 20105 self)
				(oThud lThumbLoop: 20106)
				(poTorinFallsIn setCycle: End self)
			)
			(4)
			(5)
			(6
				(poTileFalls
					setCel: 0
					posn: 82 321
					init:
					setCycle: CT 2 1 self
				)
			)
			(7 (curRoom newRoom: 21000))
		)
	)
)

(instance soBitternutToStartingLedge of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 18 518)
				(ego setScaler: Scaler 60 60 1 0)
				(ego setPri: 45)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 51 514 self)
			)
			(2 (ego setHeading: 135 self))
			(3
				(ego setLoop: 4 1)
				(ego setMotion: MoveTo 88 463 self)
			)
			(4
				(ego setLoop: -1)
				(localproc_0aa3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTorinClimbsUpTree of Prop
	(properties
		x 198
		y 1845
		view 20109
		loop 1
	)
)

(instance soClimbUpBigTree of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theSound nHeight: 20117)
				(ego
					setScaler: Scaler 60 60 1 0
					posn: 198 1845
					setLoop: 7
					hide:
				)
				(poTorinClimbsUpTree
					setCel: 0
					init:
					setPri: 2500
					setCycle: CT 30 1 self
				)
				(= oMessageList 1)
				(self posnList:)
			)
			(1
				(theSound lThumbLoop: 20117)
				(= ticks (poTorinClimbsUpTree cycleSpeed?))
			)
			(2
				(poTorinClimbsUpTree setCycle: End self)
			)
			(3
				(poTorinClimbsUpTree dispose:)
				(ego posn: 276 1583 show: setMotion: MoveTo 233 1570 self)
			)
			(4
				(localproc_4de1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(theSound stop:)
		(poTorinClimbsUpTree dispose:)
		(ego posn: 233 1570 show: setMotion: 0)
		(localproc_4de1)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance soClimbFromSeraglio of TPScript
	(properties)
	
	(method (init)
		(= oMessageList 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					hide:
					posn: 548 1293
					setScaler: Scaler 46 46 1 0
					oPanner: 1 -5436 4
					scrollTo:
				)
				(poTorinClimbsToSeraglio
					setCel: 24
					init:
					setCycle: Beg self
				)
				(= oMessageList 1)
				(self posnList:)
			)
			(1
				(poTorinClimbsToSeraglio dispose:)
				(ego show:)
				(localproc_5b1b)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(poTorinClimbsToSeraglio dispose:)
		(ego show:)
		(localproc_5b1b)
		(theGame handsOn:)
		(self dispose:)
	)
)

(instance soEnterFromDragonCave of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_35e3)
				(if ((ScriptID 64017 0) test: 144)
					(self cue:)
				else
					((ScriptID 64018 0)
						init:
						oPanner:
						posn: 0 1262
						setLoop: 0
						nCurPosX: 60 60
					)
					(self setScript: (ScriptID 64018 1) self)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soSkunkCaveFront of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 612 1255)
				(ego setScaler: Scaler 46 46 1 0)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 582 1293 self)
			)
			(2
				(localproc_5b1b)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soSkunkCaveBack of TPScript
	(properties
		oMessageList 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 530 1044)
				(ego setScaler: Scaler 17 17 1 0)
				(ego setPri: 65)
				(= cycles 2)
			)
			(1
				(oCliffScrollPlane sitNSpin: 0 810 self 1)
			)
			(2
				(ego setMotion: DPath 455 1013 444 1002 self)
			)
			(3
				(ego setPri: 55)
				(ego setMotion: DPath 472 989 512 958 512 932 self)
			)
			(4
				(ego
					setMotion:
						DPath
						504
						932
						484
						931
						469
						916
						478
						899
						520
						899
						520
						880
						452
						880
						self
				)
			)
			(5 (curRoom newRoom: 20900))
		)
	)
	
	(method (rememberMessage)
		(oCliffScrollPlane bSpinning:)
		(curRoom newRoom: 20900)
	)
)

(instance soLeaveManLedge of TPScript
	(properties
		oMessageList 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 452 880 setScaler: Scaler 17 17 1 0 setPri: 55)
				(= cycles 2)
			)
			(1
				(ego
					setMotion: DPath 520 880 520 899 478 899 469 916 484 931 504 932 self
				)
			)
			(2
				(ego
					setMotion: DPath 512 932 512 958 472 989 444 1002 self
				)
			)
			(3
				(ego setPri: 65)
				(ego setMotion: DPath 455 1013 530 1044 self)
			)
			(4
				(oCliffScrollPlane sitNSpin: 0 982 self 1)
			)
			(5 (curRoom newRoom: 20500))
		)
	)
	
	(method (rememberMessage)
		(oCliffScrollPlane bSpinning:)
		(curRoom newRoom: 20500)
	)
)

(instance poVeder of Prop
	(properties
		x 430
		y 872
		view 20102
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self nCurPosY: 39)
	)
)

(instance poRiver of Prop
	(properties
		x 82
		y 832
		view 20108
		cycleSpeed 10
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 50)
		(self setCycle: Fwd)
	)
)

(instance oBackView1 of ScrollView
	(properties
		y 315
		view 20110
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 4)
	)
)

(instance oBackView2 of ScrollView
	(properties
		y 631
		view 20111
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 4)
	)
)

(instance oCliffScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self 20100 0 0)
		(AddPicAt self 20101 0 316)
		(AddPicAt self 20102 0 632)
		(AddPicAt self 20103 0 948)
		(AddPicAt self 20104 0 1264)
		(AddPicAt self 20105 0 1580)
	)
	
	(method (oText)
		(self nInitPlaneY: oBackView1)
		(self nInitPlaneY: oBackView2)
		(oBackView1 kill: 1 632)
		(oBackView2 kill: 1 632)
	)
)

(instance oMyAmbience of TPSound
	(properties)
)

(instance coFadeFinished of CueMe
	(properties)
	
	(method (cue)
		(theMusic pageSize: 0)
	)
)

(instance roCliffScroller of TPRoom
	(properties
		picture -2
	)
	
	(method (init)
		(super init: &rest)
		(= plane
			(oCliffScrollPlane
				oBoogleFeatures: 0
				oExtraPlanes: 0
				init: (thePlane findData:) 1896
				yourself:
			)
		)
		(= global202 2)
		(= gToTripeMeat toTripe)
		(= gToVisceraMeat toViscera)
		(if (not ((ScriptID 64017 0) test: 73))
			(poVultures init:)
		)
		(if ((ScriptID 64017 0) test: 72)
			(voTree setLoop: 1 init:)
		else
			(voTree setLoop: 0 init:)
		)
		(poRiver init:)
		(theMusic pageSize: 0)
		(oMyAmbience vThumbView: 20100)
		(ego init: oPanner:)
		(ego setScaler: Scaler 60 60 1 0)
		(theGame handsOn:)
		(switch prevRoomNum
			(20200
				(oCliffScrollPlane fadeRel: 0 276)
				(ego setScript: soBitternutToStartingLedge)
			)
			(20300
				(oCliffScrollPlane fadeRel: 0 360)
				(ego posn: 576 516)
				(ego setLoop: 1)
				(ego setScaler: Scaler 33 33 1 0)
				(localproc_2608)
			)
			(20400
				(oCliffScrollPlane fadeRel: 0 1104)
				(ego posn: 46 1262)
				(ego setLoop: 4)
				(ego setScaler: Scaler 60 60 1 0)
				(ego setScript: soEnterFromDragonCave)
			)
			(20500
				(if ((ScriptID 64017 0) test: 84)
					(oCliffScrollPlane fadeRel: 0 982)
					(ego setScript: soSkunkCaveBack)
				else
					(oCliffScrollPlane fadeRel: 0 1104)
					(ego setScript: soSkunkCaveFront)
				)
			)
			(20900
				(oCliffScrollPlane fadeRel: 0 696)
				(ego setScript: soLeaveManLedge)
			)
			(20600
				(oCliffScrollPlane fadeRel: 0 1104)
				(curRoom setScript: soClimbFromSeraglio)
			)
			(20700
				(oCliffScrollPlane fadeRel: 0 1579)
				(ego setScript: soClimbUpBigTree)
			)
			(21000
				(localproc_0aa3)
				(oCliffScrollPlane fadeRel: 0 276)
				(cond 
					(((ScriptID 64017 0) test: 81)
						(ego
							posn: 140 463
							setLoop: 6
							setScript: soStartingLedgeToTopLedge
						)
					)
					(((ScriptID 64017 0) test: 82)
						(ego
							posn: 88 463
							setLoop: 7
							setScript: soStartingLedgeToBitternut
						)
					)
					(else
						(ego
							posn: 200 468
							setLoop: 3
							setScript: soStartingLedgeToClotheslineLedge
						)
					)
				)
			)
			(20000
				(ego
					get:
						((ScriptID 64001 0) get: 8)
						((ScriptID 64001 0) get: 9)
						((ScriptID 64001 1) get: 1)
						((ScriptID 64001 1) get: 2)
						((ScriptID 64001 1) get: 0)
						((ScriptID 64001 1) get: 5)
				)
				(curRoom setScript: soEgoFallsIn)
			)
			(else 
				(oCliffScrollPlane fadeRel: 0 1287)
				(ego posn: 38 1543 setLoop: 4 scrollTo:)
				(ego
					get:
						((ScriptID 64001 0) get: 25)
						((ScriptID 64001 0) get: 26)
						((ScriptID 64001 0) get: 9)
						((ScriptID 64001 1) get: 0)
						((ScriptID 64001 1) get: 1)
						((ScriptID 64001 0) get: 14)
						((ScriptID 64001 0) get: 15)
						((ScriptID 64001 0) get: 16)
						((ScriptID 64001 0) get: 17)
						((ScriptID 64001 0) get: 18)
						((ScriptID 64001 0) get: 33)
						((ScriptID 64001 0) get: 20)
						((ScriptID 64001 0) get: 21)
						((ScriptID 64001 0) get: 22)
				)
				(localproc_4de1)
				(Palette 1 20000)
				(if (= local8 (MakeMessageText 0 0 6 1 300))
					(TextDialog local8 continueText)
					(local8 dispose:)
					(= local8 0)
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 25))
		(ego get: ((ScriptID 64001 0) get: 26))
		(ego get: ((ScriptID 64001 0) get: 27))
		(ego get: ((ScriptID 64001 0) get: 19))
	)
)

(instance oTempKeyHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0)
		(if (not (& (event type?) $000c))
			(Prints
				{Attempt to execute key handler with non-key event. DJM keys.sc}
			)
			(return 0)
		)
		(if (not (& (event type?) evKEYBOARD)) (return 0))
		(if (== (= temp0 (event message?)) 57)
			(ego setScaler: 0)
			(ego scaleX: (- (ego scaleX?) 1))
			(ego scaleY: (- (ego scaleY?) 1))
			(ego setScale:)
			(event claimed: 1)
		)
		(if (== temp0 48)
			(ego setScaler: 0)
			(ego scaleX: (+ (ego scaleX?) 1))
			(ego scaleY: (+ (ego scaleY?) 1))
			(ego setScale:)
			(event claimed: 1)
		)
		(return (event claimed?))
	)
)
