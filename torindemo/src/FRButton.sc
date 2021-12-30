;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64002)
(include sci.sh)
(use Main)
(use Indicator)
(use TPSound)
(use GenDialog)
(use Script)
(use ModalPlane)
(use Button)
(use DmInv)
(use InvInset)
(use Plane)
(use Array)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)

(public
	oInterface 0
	oInterfacePlane 1
	proc64002_2 2
	oScoreKeeper 3
	oRewind 4
	oPause 5
	oFastForward 6
	proc64002_7 7
	proc64002_8 8
	oInvPlane 9
	oTeleprompter 10
	soTeleprompterUp 11
	soTeleprompterDown 12
)

(local
	local0
	local1
)
(procedure (proc64002_2 &tmp [temp0 3] temp3)
	(oInterfacePlane init: 0 320 lastScreenX lastScreenY)
	((OpaqueFeature new:) init: oInterfacePlane)
	(topBorder init: 0 0 lastScreenX 3)
	(leftBorder init: 0 4 3 320)
	(rightBorder init: (- screenWidth 4) 4 screenWidth 320)
	(oInvPlane init: 78 352 558 479)
	(oBooglePlane init: 78 352 558 479)
	((ScriptID 64002 3) init: oInterfacePlane)
	(foScore init: oInterfacePlane)
	(= eatMice 2)
	(= temp3
		(IntArray
			newWith:
				32
				78
				65
				143
				65
				208
				65
				273
				65
				339
				65
				404
				65
				64
				90
				135
				90
				348
				90
				419
				90
				48
				122
				125
				122
				202
				122
				279
				122
				356
				122
				433
				122
		)
	)
	((ScriptID 64001 0)
		checkStatus: -5531
		init: (oInvPlane nSpeed:) temp3
	)
	(= local1
		(((ScriptID 64897 1) new:) init: oInvPlane yourself:)
	)
	(oTorinWellBG init: oInvPlane)
	((voEyePedestal new:) init: oInvPlane)
	((ScriptID 64001 1)
		checkStatus: -5531
		init: (oBooglePlane nSpeed:) temp3
	)
	(= local0
		(((ScriptID 64897 1) new:) init: oBooglePlane yourself:)
	)
	(oBoogleWellBG init: oBooglePlane)
	((voEyePedestal new:) init: oBooglePlane)
	(temp3 dispose:)
	(= temp3 0)
	((ScriptID 64004 0)
		picture: -2
		fore: 235
		left: 105
		right: 535
		top: 227
		bottom: 327
		init:
	)
	(voTeleprompterBg
		priority: 0
		fixPriority: 1
		y: (-
			((ScriptID 64004 0) bottom?)
			((ScriptID 64004 0) top?)
		)
		init: (ScriptID 64004 0)
	)
	(oTorinInventory init: oInterfacePlane)
	(oBoogleInventory init: oInterfacePlane)
	(oInventoryGroup init: oInterfacePlane)
	(oTeleprompter init: oInterfacePlane)
	(oHelp init: oInterfacePlane)
	(oRewind init: oInterfacePlane)
	(oPause init: oInterfacePlane)
	(oFastForward init: oInterfacePlane)
	(gOEventHandler setIncrement: oRewind)
	(gOEventHandler setIncrement: oFastForward)
	(gOEventHandler setIncrement: oPause)
	(super init: oInterfacePlane)
	((ScriptID 64000 0) init:)
	(UpdatePlane (ScriptID 64002 1))
	(UpdatePlane oInvPlane)
)

(procedure (proc64002_7)
	(if (== global265 0) (return))
	(proc64897_2 local0)
	(= global265 0)
	(oBooglePlane setPri: -1)
	(oInvPlane setPri: 450)
	(oTorinInventory slotIncY: 1)
)

(procedure (proc64002_8)
	(if (== global265 1) (return))
	(proc64897_2 local1)
	(= global265 1)
	(oBooglePlane setPri: 450)
	(oInvPlane setPri: -1)
	(oBoogleInventory slotIncY: 1)
)

(instance oInterfacePlane of Plane
	(properties
		priority 400
	)
)

(instance oInvPlane of Plane
	(properties
		picture -2
		priority 450
	)
)

(instance oBooglePlane of Plane
	(properties
		picture -2
	)
)

(instance oInterface of View
	(properties
		priority 1
		view -5532
	)
	
	(method (init &tmp [temp0 3] temp3)
		(oInterfacePlane init: 0 320 lastScreenX lastScreenY)
		((OpaqueFeature new:) init: oInterfacePlane)
		(topBorder init: 0 0 lastScreenX 3)
		(leftBorder init: 0 4 3 320)
		(rightBorder init: (- screenWidth 4) 4 screenWidth 320)
		(oInvPlane init: 78 352 558 479)
		(oBooglePlane init: 78 352 558 479)
		((ScriptID 64002 3) init: oInterfacePlane)
		(foScore init: oInterfacePlane)
		(= eatMice 2)
		(= temp3
			(IntArray
				newWith:
					32
					78
					65
					143
					65
					208
					65
					273
					65
					339
					65
					404
					65
					64
					90
					135
					90
					348
					90
					419
					90
					48
					122
					125
					122
					202
					122
					279
					122
					356
					122
					433
					122
			)
		)
		((ScriptID 64001 0)
			checkStatus: -5531
			init: (oInvPlane nSpeed:) temp3
		)
		(= local1
			(((ScriptID 64897 1) new:) init: oInvPlane yourself:)
		)
		(oTorinWellBG init: oInvPlane)
		((voEyePedestal new:) init: oInvPlane)
		((ScriptID 64001 1)
			checkStatus: -5531
			init: (oBooglePlane nSpeed:) temp3
		)
		(= local0
			(((ScriptID 64897 1) new:) init: oBooglePlane yourself:)
		)
		(oBoogleWellBG init: oBooglePlane)
		((voEyePedestal new:) init: oBooglePlane)
		(temp3 dispose:)
		(= temp3 0)
		((ScriptID 64004 0)
			picture: -2
			fore: 235
			left: 105
			right: 535
			top: 227
			bottom: 327
			init:
		)
		(voTeleprompterBg
			priority: 0
			fixPriority: 1
			y: (-
				((ScriptID 64004 0) bottom?)
				((ScriptID 64004 0) top?)
			)
			init: (ScriptID 64004 0)
		)
		(oTorinInventory init: oInterfacePlane)
		(oBoogleInventory init: oInterfacePlane)
		(oInventoryGroup init: oInterfacePlane)
		(oTeleprompter init: oInterfacePlane)
		(oHelp init: oInterfacePlane)
		(oRewind init: oInterfacePlane)
		(oPause init: oInterfacePlane)
		(oFastForward init: oInterfacePlane)
		(gOEventHandler setIncrement: oRewind)
		(gOEventHandler setIncrement: oFastForward)
		(gOEventHandler setIncrement: oPause)
		(super init: oInterfacePlane)
		((ScriptID 64000 0) init:)
		(UpdatePlane (ScriptID 64002 1))
		(UpdatePlane oInvPlane)
	)
)

(instance teleMove of TPSound
	(properties
		number -5528
	)
)

(instance teleDone of TPSound
	(properties
		number -5527
	)
)

(instance chime of TPSound
	(properties
		number -5534
	)
)

(instance oSpeedSound of TPSound
	(properties)
)

(instance voEyePedestal of View
	(properties
		x 199
		y 73
		priority 1
		fixPriority 1
		view -5532
		loop 24
	)
)

(instance oTorinWellBG of InventWellFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nLevel (ScriptID 64001 0))
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 126 51 40 431 40 480 126
					yourself:
				)
		)
	)
)

(instance oBoogleWellBG of InventWellFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nLevel (ScriptID 64001 1))
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 126 51 40 431 40 480 126
					yourself:
				)
		)
	)
)

(instance oTorinInventory of RadioButton
	(properties
		noun 82
		x 139
		y 30
		view -5532
		loop 15
		slotIncX 1
	)
	
	(method (oVerbs)
		(if slotIncX (proc64002_7))
	)
)

(instance voTeleprompterBg of View
	(properties
		view -5526
		cel 9
	)
)

(instance poTeleprompter of Prop
	(properties
		view -5526
	)
)

(instance oTeleprompterTempPlane of Plane
	(properties
		picture -2
	)
)

(instance soTeleprompter2 of Script
	(properties)
)

(instance soTeleprompterUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ResCheck 141 -5528)
					(Load 141 -5528)
				else
					(Load 140 -5528)
				)
				(if (ResCheck 141 -5527)
					(Load 141 -5527)
				else
					(Load 140 -5527)
				)
				(oTeleprompterTempPlane
					priority: 1000
					init:
						((ScriptID 64004 0) left:)
						((ScriptID 64004 0) top?)
						((ScriptID 64004 0) right:)
						((ScriptID 64004 0) bottom?)
				)
				(UpdatePlane oTeleprompterTempPlane)
				(poTeleprompter
					y: (-
						(oTeleprompterTempPlane bottom?)
						(oTeleprompterTempPlane top?)
					)
					cel: 0
					init: oTeleprompterTempPlane
					setCycle: End self
				)
				(teleMove vThumbView: -5528)
			)
			(1
				(teleMove stop:)
				(oTeleprompterTempPlane dispose:)
				((ScriptID 64004 0) show:)
				(self dispose:)
			)
		)
	)
)

(instance soTeleprompterDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ResCheck 141 -5528)
					(Load 141 -5528)
				else
					(Load 140 -5528)
				)
				(if (ResCheck 141 -5527)
					(Load 141 -5527)
				else
					(Load 140 -5527)
				)
				((ScriptID 64004 0) hide:)
				(oTeleprompterTempPlane
					priority: 1000
					init:
						((ScriptID 64004 0) left:)
						((ScriptID 64004 0) top?)
						((ScriptID 64004 0) right:)
						((ScriptID 64004 0) bottom?)
				)
				(UpdatePlane oTeleprompterTempPlane)
				(poTeleprompter
					y: (-
						(oTeleprompterTempPlane bottom?)
						(oTeleprompterTempPlane top?)
					)
					cel: 9
					init: oTeleprompterTempPlane
					setCycle: Beg self
				)
				(teleMove vThumbView: -5528)
			)
			(1
				(teleMove stop:)
				(teleDone lThumbLoop:)
				(oTeleprompterTempPlane dispose:)
				(self dispose:)
			)
		)
	)
)

(instance oTeleprompter of ToggleButton
	(properties
		noun 84
		x 54
		y 62
		view -5532
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 75 42 54 71 0 70 0 39 21 18 75 18
					yourself:
				)
		)
	)
	
	(method (oVerbs)
		(if slotIncX
			(soTeleprompterUp init: soTeleprompter2)
		else
			(soTeleprompterDown init: soTeleprompter2)
		)
	)
)

(instance poHourglass of Prop
	(properties
		x 609
		y 70
		view -5532
		loop 25
	)
)

(instance oHelp of PushButton
	(properties
		noun 85
		x 609
		y 70
		view -5532
		loop 17
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 579 59 591 50 615 51 632 59 635 66 613 71 586 66
					yourself:
				)
		)
	)
	
	(method (oVerbs &tmp temp0)
		(if (== loop 17)
			(theGame setScript: soRaiseHourglass)
		else
			(theGame setScript: soLowerHourglass)
		)
	)
)

(instance soRaiseHourglass of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (ResCheck 141 -5528)
					(Load 141 -5528)
				else
					(Load 140 -5528)
				)
				(if (ResCheck 141 -5527)
					(Load 141 -5527)
				else
					(Load 140 -5527)
				)
				(if (= temp0 (MakeMessageText 19 0 10 1 300))
					(TextDialog temp0 continueText)
					(temp0 dispose:)
					(= temp0 0)
				)
				(oHelp hide:)
				(poHourglass
					cel: (poHourglass lastCel:)
					init: (oHelp plane?)
					setCycle: Beg self
				)
				(teleMove vThumbView: -5528)
			)
			(1
				(teleMove stop:)
				(teleDone lThumbLoop:)
				(poHourglass dispose:)
				(oHelp loop: 25 show:)
				(oHelp
					setPolygon:
						((Polygon new:)
							type: 0
							init: 578 58 585 5 609 2 630 4 637 8 634 66 615 72 587 67
							yourself:
						)
				)
				(self dispose:)
			)
		)
	)
)

(instance soLowerHourglass of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (ResCheck 141 -5528)
					(Load 141 -5528)
				else
					(Load 140 -5528)
				)
				(if (ResCheck 141 -5527)
					(Load 141 -5527)
				else
					(Load 140 -5527)
				)
				(if (= temp0 (MakeMessageText 19 0 9 1 300))
					(TextDialog temp0 continueText)
					(temp0 dispose:)
					(= temp0 0)
				)
				(oHelp hide:)
				(poHourglass
					cel: 0
					init: (oHelp plane?)
					setCycle: End self
				)
				(teleMove vThumbView: -5528)
			)
			(1
				(teleMove stop:)
				(teleDone lThumbLoop:)
				(poHourglass dispose:)
				(oHelp loop: 17 show:)
				(oHelp
					setPolygon:
						((Polygon new:)
							type: 0
							init: 579 59 591 50 615 51 632 59 635 66 613 71 586 66
							yourself:
						)
				)
				(self dispose:)
			)
		)
	)
)

(instance oBoogleInventory of RadioButton
	(properties
		noun 81
		x 179
		y 30
		view -5532
		loop 16
	)
	
	(method (oVerbs &tmp temp0)
		(if slotIncX (proc64002_8))
		(if (= temp0 (MakeMessageText 0 0 12 1 300))
			(TextDialog temp0 continueText)
			(temp0 dispose:)
			(= temp0 0)
		)
	)
)

(instance oInventoryGroup of RadioGroup
	(properties)
	
	(method (init)
		(super init: &rest)
		(self add: oBoogleInventory oTorinInventory)
	)
)

(class FRButton of PushButton
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		removeItem 0
		ioMine 867
		curSlot 0
		setCurItem 0
		getVerbItem 868
		blankID 0
		viewSlotsX 869
		viewSlotsY 0
		invSlotsX 0
		invSlotsY 1
		nPointOn 0
	)
	
	(method (setScript newScript &tmp temp0)
		(if (and argc (== newScript nPointOn)) (return))
		(if argc (= nPointOn newScript) else (= nPointOn 0))
	)
)

(instance oRewind of FRButton
	(properties
		noun 75
		x 277
		y 30
		view -5532
		loop 7
	)
	
	(method (enable)
		(super enable: &rest)
	)
	
	(method (oVerbs)
		(if nPointOn
			(nPointOn state: 9999)
			(messager isVisible:)
			(nPointOn sayMessage:)
			(if (nPointOn oMessageList?)
				(oFastForward enable:)
				(oFastForward setScript: nPointOn)
			)
		)
	)
)

(instance oFastForward of FRButton
	(properties
		noun 74
		x 357
		y 30
		view -5532
		loop 10
	)
	
	(method (enable)
		(super enable: &rest)
	)
	
	(method (oVerbs)
		(if nPointOn
			(nPointOn state: 9999)
			(messager isVisible:)
			(nPointOn rememberMessage:)
		)
	)
)

(instance oPause of ToggleButton
	(properties
		noun 73
		x 317
		y 30
		view -5532
		loop 8
	)
	
	(method (oVerbs &tmp temp0)
		(if slotIncX
			(Sound pause: 1)
			(oPausePlane init:)
			(if (= temp0 (MakeMessageText 0 0 2 1 0 300))
				(TextDialog temp0 continueText)
				(temp0 dispose:)
				(= temp0 0)
				(self slotIncY: 0)
				(oPausePlane dispose:)
				(Sound pause: 0)
			)
		else
			(oPausePlane dispose:)
			(Sound pause: 0)
		)
	)
)

(instance oPausePlane of ModalPlane
	(properties)
	
	(method (init &tmp oPausePlane)
		(= picture -2)
		(= oPausePlane (oPause plane?))
		(super
			init:
				(oPausePlane left:)
				(oPausePlane top?)
				(oPausePlane right:)
				(oPausePlane bottom?)
		)
		(oPause dispose: init: oPausePlane slotIncY: 1)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(oPause init: oInterfacePlane)
	)
)

(instance oScoreKeeper of PointGauge
	(properties
		unregisterGlobalHandler -5526
		margin 8
		view -5529
		x 442
		y 51
	)
)

(instance foScore of Feature
	(properties
		noun 83
	)
	
	(method (init)
		(super init: &rest)
		(self setRect: 438 35 481 57)
	)
)

(instance topBorder of Plane
	(properties
		priority 400
		back 227
	)
)

(instance leftBorder of Plane
	(properties
		priority 400
		back 227
	)
)

(instance rightBorder of Plane
	(properties
		priority 400
		back 227
	)
)
