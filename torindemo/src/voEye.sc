;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64897)
(include sci.sh)
(use Main)
(use Script)
(use TranslucentPlane)
(use ModalPlane)
(use PushButton)
(use Plane)
(use Print)
(use Polygon)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	oRotInv 0
	voEye 1
	proc64897_2 2
)

(local
	local0
	local1
	local2
	local3
	poRotViewSel_256
	theGInventItem
	theView
)
(procedure (proc64897_2 param1)
	(if (and theGInventItem (== param1 theView))
		(if (not theView)
			(MonoOut
				{Big error -- inconsistent vars in invinset. djm}
			)
			(return)
		)
		(theGInventItem moveTo: -3)
		(= theGInventItem 0)
		(theView view: -5531 loop: 0 cel: 0)
		(oRotInv dispose:)
	)
)

(instance poRotView of Prop
	(properties
		x 300
		y 200
	)
	
	(method (init)
		(super init: &rest)
		(= local1 (+ (self lastCel:) 1))
		(if
			(OneOf
				theGInventItem
				((ScriptID 64001 0) get: 31)
				((ScriptID 64001 0) get: 32)
				((ScriptID 64001 0) get: 35)
				((ScriptID 64001 0) get: 36)
			)
			(poRotView
				setVisibleRange: 1
				signal: (& (poRotView signal?) $efff)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGInventItem moveTo: -1)
				(switch theGInventItem
					(((ScriptID 64001 0) get: 31)
						(= theGInventItem ((ScriptID 64001 0) get: 32))
					)
					(((ScriptID 64001 0) get: 32)
						(= theGInventItem ((ScriptID 64001 0) get: 31))
					)
					(((ScriptID 64001 0) get: 35)
						(= theGInventItem ((ScriptID 64001 0) get: 36))
					)
					(((ScriptID 64001 0) get: 36)
						(= theGInventItem ((ScriptID 64001 0) get: 35))
					)
				)
				(= view (theGInventItem oParent?))
				(theGInventItem moveTo: -4)
				(voEye
					view: (theGInventItem bHilited?)
					loop: (theGInventItem bChecked?)
					cel: (theGInventItem oChildren?)
				)
			)
		)
	)
)

(instance voEye of View
	(properties
		noun 5
		x 246
		y 90
		view -5531
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						243
						74
						266
						75
						283
						82
						283
						89
						271
						98
						247
						99
						225
						98
						206
						90
						205
						83
						217
						74
						237
						72
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (and theGInventItem (== theVerb 1))
			(if
			(== theGInventItem ((ScriptID 64001 0) get: 52))
				(curRoom arrowDown: (ScriptID 56000 1))
			else
				(oRotInv dispose:)
			)
			(theGInventItem moveTo: -2)
			(= theGInventItem 0)
			(= theView 0)
			(= view -5531)
			(= cel (= loop 0))
			(return)
		)
		(if gInventItem
			(= theGInventItem gInventItem)
			(= theView self)
			(gInventItem moveTo: -4)
			(= view (theGInventItem bHilited?))
			(= loop (theGInventItem bChecked?))
			(= cel (theGInventItem oChildren?))
			(if
			(== theGInventItem ((ScriptID 64001 0) get: 52))
				(curRoom initThumb: (ScriptID 56000 1))
			else
				(oRotInv init:)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp temp0 temp1)
		(if (== argc 1)
			(= temp0 (theObjOrX x?))
			(= temp1 (theObjOrX y?))
		else
			(= temp0 theObjOrX)
			(= temp1 theY)
		)
		(if (super onMe: temp0 temp1)
		else
			(OnMe temp0 temp1 self 1)
		)
	)
	
	(method (setSpeedDirect param1)
		(return
			(if
				(or
					(and
						(not theGInventItem)
						(or
							((ScriptID 64001 0) addItems: param1)
							((ScriptID 64001 1) addItems: param1)
						)
					)
					(and theGInventItem (== param1 1))
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance soScanObject of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poDarkerPlane init:)
				(poRotView init: (oRotInv nSpeed:))
				(oRotateForward slotIncY: 1 oVerbs:)
				(theGame handsOn:)
			)
		)
	)
)

(instance foOpaque of OpaqueFeature
	(properties)
)

(instance poDarkerPlane of TranslucentPlane
	(properties
		priority 609
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(foOpaque init: self)
	)
)

(instance oRotInv of Plane
	(properties
		picture -2
		priority 610
	)
	
	(method (init &tmp temp0 [temp1 3])
		(super init: 16 8 616 408 &rest)
		(if (not theGInventItem)
			(Prints
				{attempt to bring up rotating view with non-item. invinset.sc djm}
			)
			(return)
		)
		(oRotateReverse init: (ScriptID 64002 1))
		(oStepReverse init: (ScriptID 64002 1))
		(oStop init: (ScriptID 64002 1))
		(oStepForward init: (ScriptID 64002 1))
		(oRotateForward init: (ScriptID 64002 1))
		(oButtonGroup init:)
		((ScriptID 64002 5) hide:)
		((ScriptID 64002 4) hide:)
		((ScriptID 64002 6) hide:)
		(= temp0 (theGInventItem oParent?))
		(poRotView
			view: temp0
			setPri: 100
			setScript: soScanObject
		)
		(UpdatePlane self)
	)
	
	(method (dispose)
		(oRotateReverse dispose:)
		(oStepReverse dispose:)
		(oStop dispose:)
		(oStepForward dispose:)
		(oRotateForward dispose:)
		(oButtonGroup dispose:)
		((ScriptID 64002 5) show:)
		((ScriptID 64002 4) show:)
		((ScriptID 64002 6) show:)
		(if (poDarkerPlane nScreenSizeX?)
			(poDarkerPlane dispose:)
		)
		(super dispose: &rest)
	)
)

(instance soCloseRotInv of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (oRotInv dispose:))
		)
	)
)

(instance oRotateReverse of RadioButton
	(properties
		noun 79
		x 237
		y 30
		view -5532
		loop 2
	)
	
	(method (oVerbs)
		(poRotView setCycle: Fwd)
	)
)

(instance oStepReverse of PushButton
	(properties
		noun 77
		x 277
		y 30
		view -5532
		loop 3
	)
	
	(method (oVerbs)
		(poRotView setCycle: 0)
		(oRotateReverse slotIncY: 0)
		(oRotateForward slotIncY: 0)
	)
	
	(method (oMyHandler)
		(poRotView setCycle: 0)
		(if
			(>
				(= poRotViewSel_256 (+ (poRotView cel:) 1))
				(poRotView lastCel:)
			)
			(= poRotViewSel_256 0)
		)
		(poRotView cel: poRotViewSel_256)
		(= local2 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local2)
			(poRotView setCycle: 0)
			(if
				(>
					(= poRotViewSel_256 (+ (poRotView cel:) 1))
					(poRotView lastCel:)
				)
				(= poRotViewSel_256 0)
			)
			(poRotView cel: poRotViewSel_256)
			(= local2 (+ gameTime 5))
		)
	)
)

(instance oStop of PushButton
	(properties
		noun 80
		x 317
		y 30
		view -5532
		loop 4
	)
	
	(method (oVerbs)
		(poRotView setCycle: 0)
		(oRotateReverse slotIncY: 0)
		(oRotateForward slotIncY: 0)
	)
)

(instance oStepForward of PushButton
	(properties
		noun 76
		x 358
		y 30
		view -5532
		loop 5
	)
	
	(method (oVerbs)
		(poRotView setCycle: 0)
		(oRotateReverse slotIncY: 0)
		(oRotateForward slotIncY: 0)
	)
	
	(method (oMyHandler)
		(poRotView setCycle: 0)
		(if
		(< (= poRotViewSel_256 (- (poRotView cel:) 1)) 0)
			(= poRotViewSel_256 (poRotView lastCel:))
		)
		(poRotView cel: poRotViewSel_256)
		(= local2 (+ gameTime 20))
	)
	
	(method (addSelfToCursorList)
		(if (> gameTime local2)
			(if
			(< (= poRotViewSel_256 (- (poRotView cel:) 1)) 0)
				(= poRotViewSel_256 (poRotView lastCel:))
			)
			(poRotView cel: poRotViewSel_256)
			(= local2 (+ gameTime 5))
		)
	)
)

(instance oRotateForward of RadioButton
	(properties
		noun 78
		x 399
		y 30
		view -5532
		loop 6
	)
	
	(method (oVerbs)
		(poRotView setCycle: Rev)
	)
)

(instance oButtonGroup of RadioGroup
	(properties)
	
	(method (init)
		(super init: &rest)
		(self add: oRotateReverse oRotateForward)
	)
)
