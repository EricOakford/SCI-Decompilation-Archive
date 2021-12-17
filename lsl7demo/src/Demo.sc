;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
(use Main)
(use TPSound)
(use DialogPlane)
(use L7Room)
(use TiledBitmap)
(use CueMe)
(use NewUser)
(use PushButton)
(use GenDialog)
(use Talker)
(use Osc)
(use ForCount)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	roDemoRoom 0
)

(local
	local0
	local1
)
(procedure (localproc_1a62 param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 temp3 newTextItemNHeight_2 newDismissTextButtonNWidth newDismissTextButtonNHeight temp7 temp8 newDialogPlane newTiledView newDismissTextButton temp12 newDismissTextButton_2 newTiledViewNWidth newTextItemNHeight newTiledViewNOffsetX newTiledViewNOffsetY temp18 temp19 newTextItem temp21 temp22)
	(if
	(or (< argc 3) (not param1) (not param2) (not param3))
		(if param1 (proc64896_7 param1))
		(if param2 (proc64896_7 param2))
		(if param3 (proc64896_7 param3))
		(PrintDebug
			{Illegal call of MakeChoice procedure. demo.sc SRC}
		)
		(return 0)
	)
	(if (< argc 6) (= temp1 -2) else (= temp1 param4))
	(if (< argc 7) (= temp2 -2) else (= temp2 param5))
	(if (< argc 8) (= temp0 300) else (= temp0 param6))
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= temp3 400)
	(= temp21
		(+
			(= temp21
				(Max
					(GetTextWidth param2 global270 0)
					(GetTextWidth param3 global270 0)
				)
			)
			10
		)
	)
	(= newDismissTextButton (DismissTextButton new:))
	(newDismissTextButton
		font: global270
		nLeading: global271
		fore: 0
		back: gBack
		text: (Array 8 (Array 9 param2))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 1
		bDefault: 1
		init: newDialogPlane
	)
	(= newDismissTextButton_2 (DismissTextButton new:))
	(newDismissTextButton_2
		font: global270
		nLeading: global271
		fore: 0
		back: gBack
		text: (Array 8 (Array 9 param3))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 0
		init: newDialogPlane
	)
	(= newDismissTextButtonNWidth
		(newDismissTextButton nWidth?)
	)
	(= newDismissTextButtonNHeight
		(newDismissTextButton nHeight?)
	)
	(= temp7 (+ (Max temp3 newDismissTextButtonNWidth) 10))
	(if param1
		(= newTextItem (TextItem new:))
		(newTextItem
			font: global268
			nLeading: global269
			maxWidth: temp0
			nMinWidth: (+ temp7 40)
			fore: 0
			back: 255
			skip: 255
			text: (Array 8 (Array 9 param1))
			border: 5
			bTileBorder: 0
			vTile: -5516
			init: newDialogPlane
		)
		(= newTextItemNHeight (newTextItem nHeight?))
		(= newTextItemNHeight_2 (newTextItem nHeight?))
	else
		(= newTextItem 0)
	)
	(= temp8
		(+
			newTextItemNHeight_2
			(* newDismissTextButtonNHeight 2)
		)
	)
	(if param1 (= temp22 -5517) else (= temp22 global275))
	((= newTiledView (TiledView new:))
		view: temp22
		init: temp7 temp8 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTextItemNHeight
		(+ newTextItemNHeight (newTiledView nHeight?))
	)
	(switch temp1
		(-1
			(= temp1 (/ (- screenWidth newTiledViewNWidth) 2))
		)
		(-2
			(= temp1
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
		)
	)
	(switch temp2
		(-1
			(= temp2 (/ (- screenHeight newTextItemNHeight) 2))
		)
		(-2
			(= temp2
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTextItemNHeight) 2)
				)
			)
		)
	)
	(newDialogPlane
		setRect:
			temp1
			temp2
			(- (+ temp1 newTiledViewNWidth) 1)
			(- (+ temp2 newTextItemNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledViewNOffsetX (newTiledView nOffsetX?))
	(= newTiledViewNOffsetY (newTiledView nOffsetY?))
	(if newTextItem
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem nHeight?))
		)
	)
	(= temp18
		(/
			(- newTiledViewNWidth (+ newDismissTextButtonNWidth 5))
			2
		)
	)
	(= temp19 (+ newTiledViewNOffsetY 10))
	(newDismissTextButton posn: temp18 temp19)
	(newDismissTextButton_2
		posn: temp18 (+
			(newDismissTextButton y?)
			newDismissTextButtonNHeight
			5
		)
	)
	(if param1 (proc64896_7 param1))
	(if param2 (proc64896_7 param2))
	(if param3 (proc64896_7 param3))
	(return (newDialogPlane sitNSpin:))
)

(procedure (localproc_1fc4 param1)
	(if (ResCheck 139 param1)
		(Palette 1 param1)
	else
		(PrintDebug {Palette %d is inaccessible.} param1)
	)
	(return 1)
)

(instance oShortMusic of TPSound
	(properties
		type $0001
	)
)

(instance oOceanSound of TPSound
	(properties
		type $0001
	)
)

(instance roDemoRoom of L7Room
	(properties
		picture 10100
	)
	
	(method (init &tmp temp0)
		(theGame handsOff:)
		(Palette 2 0 255 100)
		(gOEventHandler registerGlobalHandler: oAnyEventHandler)
		(= gToLarry toLarry)
		(= gToThygh toThygh)
		(super init: &rest)
		(self setScript: soPlayDemo)
	)
	
	(method (dispose)
		(= gToLarry 0)
		(= gToThygh 0)
		(super dispose: &rest)
	)
	
	(method (newRoom)
		(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
		(super newRoom: &rest)
	)
)

(instance oSlap of TPSound
	(properties)
)

(instance poShakingLarry of Prop
	(properties)
)

(instance poStandingLarry of Prop
	(properties
		priority 1
		fixPriority 1
	)
)

(instance poLoneLarryEyes of Prop
	(properties
		x 322
		y 480
		priority 300
		fixPriority 1
		view 10501
		loop 1
	)
)

(instance poDewmi of Prop
	(properties)
)

(instance poDewmiHand of Prop
	(properties)
)

(instance poGangwayCrowd of Prop
	(properties
		x 87
		y 253
		view 10101
	)
)

(instance poShipCrowd of Prop
	(properties
		x 51
		y 402
		view 10101
		loop 1
	)
)

(instance poFullThygh of Prop
	(properties
		x 453
		y 467
		priority 3
		fixPriority 1
		view 10300
	)
)

(instance poFullThyghArm of Prop
	(properties
		x 453
		y 467
		priority 5
		fixPriority 1
		view 10300
		cel 1
	)
)

(instance poWheelchair of Prop
	(properties
		x 453
		y 467
		priority 2
		fixPriority 1
		view 10301
	)
)

(instance poMrBoning of Prop
	(properties
		x 453
		y 467
		priority 4
		fixPriority 1
		view 10301
		cel 1
		cycleSpeed 10
	)
)

(instance poWheelchairOut of Prop
	(properties
		x 453
		y 467
		priority 500
		fixPriority 1
		view 10302
		cycleSpeed 10
	)
)

(instance poBugEyeLarry of Prop
	(properties
		x 453
		y 467
		priority 1
		fixPriority 1
		view 10305
	)
)

(instance poHandShake of Prop
	(properties
		x 322
		y 480
		priority 300
		fixPriority 1
		view 10500
		loop 2
		cycleSpeed 6
	)
)

(instance poLarryArm of Prop
	(properties
		x 214
		y 480
		view 10700
		loop 2
		cycleSpeed 6
	)
)

(instance poTikiFlames of Prop
	(properties
		x 143
		y 153
		priority 100
		fixPriority 1
		view 11350
		cycleSpeed 6
	)
)

(instance poNeonSign of Prop
	(properties
		x 318
		y 103
		priority 50
		fixPriority 1
		view 11355
		loop 1
		cycleSpeed 21
	)
)

(instance poFlashNips of Prop
	(properties
		x 318
		y 103
		priority 40
		fixPriority 1
		view 11355
		cycleSpeed 15
	)
)

(instance aoBoxAnimation of Actor
	(properties
		x 200
		y 240
		view 11000
		cycleSpeed 12
	)
)

(instance coEndTalk of CueMe
	(properties)
	
	(method (cue)
		(= local1 1)
	)
)

(instance poLarry of Prop
	(properties
		x 340
		y 322
		view 10400
	)
)

(instance voLarry of View
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 1))
		(super init: &rest)
	)
	
	(method (show)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 1))
		(super show: &rest)
	)
)

(instance toLarry of Talker
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 1))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(= priority (+ (poLarry priority?) 1))
		(voLarry hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voLarry show:)
		(super dispose: &rest)
	)
)

(instance poLarryEyes of Prop
	(properties)
	
	(method (init)
		(= view (poLarry view?))
		(= loop (+ (poLarry loop?) 2))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(self setPri: (+ (poLarry priority?) 15))
		(super init: &rest)
		(self cycleSpeed: 6)
		(self setCycle: Blink 10)
	)
	
	(method (show)
		(= view (poLarry view?))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(super show: &rest)
		(self setCycle: Blink 10)
	)
)

(instance poThygh of Prop
	(properties
		x 296
		y 264
		priority 5
		fixPriority 1
		view 10600
	)
)

(instance voThygh of View
	(properties)
	
	(method (init)
		(= view (poThygh view?))
		(= loop (+ (poThygh loop?) 1))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(self setPri: (+ (poThygh priority?) 1))
		(super init: &rest)
	)
	
	(method (show)
		(= view (poThygh view?))
		(= loop (+ (poThygh loop?) 1))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(self setPri: (+ (poThygh priority?) 1))
		(super show: &rest)
	)
)

(instance toThygh of Talker
	(properties)
	
	(method (init)
		(= view (poThygh view?))
		(= loop (+ (poThygh loop?) 1))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(= priority (+ (poThygh priority?) 1))
		(voThygh hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voThygh show:)
		(super dispose: &rest)
	)
)

(instance toNoMouthThygh of Talker
	(properties)
	
	(method (init)
		(= view (poThygh view?))
		(= loop (+ (poThygh loop?) 1))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(= priority (+ (poThygh priority?) 1))
		(voThygh hide:)
		(super init: &rest)
	)
)

(instance poThyghEyes of Prop
	(properties)
	
	(method (init)
		(= view (poThygh view?))
		(= loop (+ (poThygh loop?) 2))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(self setPri: (+ (poThygh priority?) 15))
		(super init: &rest)
		(self cycleSpeed: 6)
		(self setCycle: Blink 10)
	)
	
	(method (show)
		(= view (poThygh view?))
		(= x (poThygh x?))
		(= y (poThygh y?))
		(super show: &rest)
		(self setCycle: Blink 10)
	)
)

(instance soPlayDemo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load 140 10001)
				(poShakingLarry
					view: 10100
					loop: 0
					cel: 0
					cycleSpeed: 6
					x: 275
					y: 284
					init:
				)
				(if ((ScriptID 64017 0) test: 14)
					(= cycles 1)
				else
					(= local1 0)
					(messager say: 0 0 0 1 coEndTalk 1)
					(= cycles 1)
				)
			)
			(1
				(gOMusic1 stop:)
				(oShortMusic playSound: 10000)
				(poShakingLarry
					loop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
				(gOSound1 playSound: 10001)
			)
			(2
				(Load 140 10002)
				(poShakingLarry
					loop: 1
					cel: 0
					cycleSpeed: 11
					setCycle: End self
				)
			)
			(3
				(poShakingLarry
					loop: 1
					cel: 0
					cycleSpeed: 6
					setCycle: ForwardCounter 3 self
				)
			)
			(4
				(poShakingLarry
					loop: 1
					cycleSpeed: 3
					setCycle: ForwardCounter 5 self
				)
			)
			(5
				(gOSound1 playSound: 10002)
				(poShakingLarry
					loop: 2
					cycleSpeed: 2
					setCycle: ForwardCounter 3 self
				)
				(oShortMusic dispose:)
			)
			(6
				(Load rsPIC 10200)
				(if ((ScriptID 64017 0) test: 14)
					(= ticks 200)
				else
					(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
					(= ticks 200)
				)
			)
			(7
				(if ((ScriptID 64017 0) test: 14)
					(= cycles 1)
				else
					(= local0
						(localproc_1a62
							(MakeMessageText 1 0 5 1 1)
							(MakeMessageText 1 0 3 1 1)
							(MakeMessageText 1 0 2 1 1)
						)
					)
				)
				(= cycles 1)
			)
			(8
				(if (not local1) (messager kill:))
				(= cycles 1)
			)
			(9
				(cond 
					(((ScriptID 64017 0) test: 14) (= cycles 1))
					((== local0 1) (messager say: 0 0 0 2 self 1))
					(else (messager say: 0 0 0 3 self 1) (Load rsPIC 10200))
				)
			)
			(10
				(cond 
					(((ScriptID 64017 0) test: 14) (= cycles 1))
					((== local0 1) (= quit 1))
					(else ((ScriptID 64017 0) set: 14) (= cycles 1))
				)
			)
			(11
				(oOceanSound setAmbient: 13001)
				(gOEventHandler registerGlobalHandler: oAnyEventHandler)
				(= local1 0)
				(messager say: 0 0 0 4 coEndTalk 1)
				(poShakingLarry dispose:)
				(localproc_1fc4 101)
				(roDemoRoom drawPic: 10200)
				(poGangwayCrowd init: setCycle: Fwd)
				(poShipCrowd init: setCycle: Fwd)
				(= cycles 1)
			)
			(12
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(13
				(= local1 0)
				(messager say: 0 0 0 15 coEndTalk 1)
				(localproc_1fc4 103)
				(roDemoRoom drawPic: 10300)
				(poGangwayCrowd dispose:)
				(poShipCrowd dispose:)
				(poBugEyeLarry init:)
				(poFullThygh init:)
				(poFullThyghArm init:)
				(poWheelchair init:)
				(poMrBoning init:)
				(= ticks 60)
			)
			(14
				(poFullThyghArm hide:)
				(poMrBoning
					view: 10301
					loop: 1
					cel: 0
					x: 453
					y: 467
					setCycle: End self
				)
			)
			(15
				(poFullThyghArm show:)
				(poWheelchairOut init: setCycle: CT 17 1 self)
				(poWheelchair dispose:)
				(poMrBoning dispose:)
			)
			(16
				(poWheelchairOut dispose:)
				(poBugEyeLarry
					view: 10305
					loop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: End self
				)
				(Load rsPIC 10400)
				(poLarry init: hide:)
			)
			(17
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(18
				(poFullThygh dispose:)
				(poFullThyghArm dispose:)
				(poBugEyeLarry dispose:)
				(localproc_1fc4 104)
				(roDemoRoom drawPic: 10400)
				(poLarry view: 10400 x: 340 y: 322 show:)
				(UpdateScreenItem poLarry)
				(voLarry init:)
				(messager say: 0 0 0 5 self 1)
			)
			(19
				(localproc_1fc4 105)
				(roDemoRoom drawPic: 10500)
				(poLarry hide:)
				(voLarry hide:)
				(poStandingLarry
					view: 10501
					loop: 0
					cel: 0
					x: 322
					y: 480
					init:
				)
				(poLoneLarryEyes
					view: 10501
					loop: 1
					cel: 0
					x: 322
					y: 480
					cycleSpeed: 11
					setCycle: Osc 12
					init:
				)
				(poThygh
					view: 10500
					x: 322
					y: 480
					priority: 5
					init:
					show:
				)
				(UpdateScreenItem poThygh)
				(voThygh init:)
				(poHandShake init: setCycle: Osc 8)
				(messager say: 0 0 0 6 self 1)
			)
			(20
				(localproc_1fc4 106)
				(roDemoRoom drawPic: 10600)
				(poStandingLarry hide:)
				(poLoneLarryEyes hide:)
				(poHandShake dispose:)
				(poThygh view: 10600 loop: 0 cel: 0 x: 296 y: 264 init:)
				(voThygh view: 10600 loop: 1 cel: 0 x: 296 y: 264 init:)
				(= ticks 15)
			)
			(21
				(poThygh view: 10600 x: 296 y: 264)
				(voThygh hide:)
				(messager say: 0 0 0 14 self 1)
			)
			(22
				(poThygh hide:)
				(voThygh hide:)
				(localproc_1fc4 107)
				(roDemoRoom drawPic: 10400)
				(poLarry view: 10700 x: 214 y: 480 show:)
				(UpdateScreenItem poLarry)
				(voLarry show:)
				(poLarryArm init: setCycle: Osc 8)
				(messager say: 0 0 0 7 self 1)
				(oSlap preload: 13300)
			)
			(23
				(localproc_1fc4 108)
				(poLarry hide:)
				(voLarry hide:)
				(poLarryArm dispose:)
				(oSlap playSound: 13300)
				(poStandingLarry
					view: 10800
					loop: 0
					cel: 0
					x: 214
					y: 480
					show:
					setCycle: End self
				)
			)
			(24
				(= local1 0)
				(poStandingLarry hide:)
				(roDemoRoom drawPic: 10850)
				(oOceanSound stop:)
				(gOMusic1 stop: setMusic: 13000)
				(messager say: 0 0 0 8 coEndTalk 1)
				(= ticks 180)
			)
			(25
				(localproc_1fc4 109)
				(roDemoRoom drawPic: 10900)
				(poStandingLarry
					view: 10900
					cycleSpeed: 6
					x: 200
					y: 353
					show:
					setCycle: End self
				)
			)
			(26
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(27
				(= local1 0)
				(messager say: 0 0 0 9 coEndTalk 1)
				(localproc_1fc4 110)
				(roDemoRoom drawPic: 11000)
				(poStandingLarry hide:)
				(aoBoxAnimation
					init:
					setSpeed: 1
					setStep: 20 20
					setMotion: MoveTo 200 150 self
				)
			)
			(28
				(aoBoxAnimation setSpeed: 6 setCycle: End self)
			)
			(29
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(30
				(poThygh hide: view: 11100 x: 391 y: 412)
				(= local1 0)
				(messager say: 0 0 0 10 coEndTalk 1)
				(aoBoxAnimation hide:)
				(localproc_1fc4 111)
				(roDemoRoom drawPic: 11100)
				(poStandingLarry
					view: 11101
					loop: 0
					cel: 0
					x: 391
					y: 412
					show:
				)
				(poLoneLarryEyes
					view: 11101
					loop: 1
					cel: 0
					x: 391
					y: 412
					cycleSpeed: 6
					show:
				)
				(poDewmi
					view: 11100
					loop: 3
					cel: 0
					x: 391
					y: 412
					cycleSpeed: 21
					init:
					setCycle: End self
				)
			)
			(31
				(poDewmi hide:)
				(poThygh show:)
				(poDewmiHand
					view: 11100
					setPri: (+ 5 (poStandingLarry priority?))
					loop: 2
					cel: 0
					x: 391
					y: 412
					cycleSpeed: 8
					init:
					setCycle: End self
				)
			)
			(32
				(poLoneLarryEyes
					view: 11101
					loop: 2
					cel: 0
					cycleSpeed: 20
					setCycle: End self
				)
			)
			(33
				(poLoneLarryEyes
					view: 11101
					loop: 3
					cel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(34
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(35
				(localproc_1fc4 112)
				(roDemoRoom drawPic: 11200)
				(poDewmiHand hide:)
				(poThygh hide:)
				(voThygh hide:)
				(poStandingLarry hide:)
				(poLoneLarryEyes hide:)
				(poLarry view: 11200 x: 0 y: 478 show:)
				(UpdateScreenItem poLarry)
				(Load rsPIC 11300)
				(voLarry show:)
				(poLarryEyes init: cel: 4 setCycle: Beg self)
				(messager say: 0 0 0 11 self 1)
			)
			(36 0)
			(37
				(messager say: 0 0 0 16 self 1)
				(poLarryEyes cycleSpeed: 9 setCycle: End self)
			)
			(38 0)
			(39
				(localproc_1fc4 113)
				(poTikiFlames init: setCycle: Fwd)
				(poFlashNips init: setCycle: Fwd)
				(poNeonSign init: setCycle: Fwd)
				(roDemoRoom drawPic: 11300)
				(poLarry hide:)
				(voLarry hide:)
				(poLarryEyes hide:)
				(poThygh
					view: 11300
					loop: 0
					cel: 0
					x: 284
					y: 479
					priority: 500
					show:
				)
				(UpdateScreenItem poThygh)
				(voThygh show:)
				(= local1 0)
				(= gToThygh toNoMouthThygh)
				(messager say: 0 0 0 12 coEndTalk 1)
				(= ticks 240)
			)
			(40
				(poThygh hide:)
				(voThygh hide:)
				(if (not (talkers isEmpty:))
					((toNoMouthThygh mouth?) hide:)
				)
				(poDewmi
					view: 11300
					loop: 2
					cel: 0
					x: 284
					y: 479
					cycleSpeed: 7
					show:
					setCycle: End self
				)
			)
			(41
				(if (not local1) (= state (- state 1)))
				(= cycles 1)
			)
			(42
				(poTikiFlames dispose:)
				(poFlashNips dispose:)
				(poNeonSign dispose:)
				(poDewmi dispose:)
				(roDemoRoom drawPic: 11200)
				(poLarry view: 11400 loop: 0 cel: 0 x: 0 y: 478 show:)
				(UpdateScreenItem poLarry)
				(localproc_1fc4 114)
				(voLarry show:)
				(poLarryEyes
					show:
					loop: 2
					cel: 2
					cycleSpeed: 21
					setCycle: Rev
				)
				(messager say: 0 0 0 13 self 1)
			)
			(43
				(if ((ScriptID 64017 0) test: 6)
					(gOMusic1 fadeOut: 6 2)
					(proc64896_1 1 1 self)
				else
					(= cycles 1)
				)
			)
			(44
				(if ((ScriptID 64017 0) test: 6)
					(curRoom newRoom: 9000)
				else
					(curRoom newRoom: 200)
				)
				(self dispose:)
			)
		)
	)
)

(instance oAnyEventHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(== (event type?) evMOUSEBUTTON)
				(== (event type?) evKEYBOARD)
			)
			(if
				(and
					(<= emRIGHT_SHIFT (event modifiers?))
					(<= (event modifiers?) emALT)
				)
				(return 0)
			)
			(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
			(switch
				(= temp0
					(proc64033_7
						4
						0
						0
						(MakeMessageText 0 0 0 5 300)
						(MakeMessageText 0 0 0 2 300)
						(MakeMessageText 0 0 0 3 300)
						(MakeMessageText 0 0 0 4 300)
					)
				)
				(0 (= temp0 0))
				(1
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) set: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) clear: 8)
					(curRoom newRoom: 9000)
					(return 1)
				)
				(2
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) set: 8)
				)
				(3
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) clear: 7)
					((ScriptID 64017 0) clear: 8)
					(= quit 1)
					(return 1)
				)
			)
			(gOEventHandler registerGlobalHandler: oAnyEventHandler)
		)
		(return 1)
	)
)
