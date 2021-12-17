;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51100)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use ModalPlane)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roInsideNullVoid 0
)

(local
	local0
	local1
	local2
	local3
)
(procedure (localproc_0068 param1 param2 &tmp temp0 temp1 temp2)
	(= temp0 (- param1 474))
	(= temp1 (- param2 474))
	(= temp2
		(+ (MulDiv temp0 temp0 30) (MulDiv temp1 temp1 30))
	)
)

(instance foLycentiaExit of Feature
	(properties
		x 750
		y 700
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 745 689 710 754 650 725 690 660
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (onMe &tmp temp0)
		(= temp0
			(if (super onMe: (ego x?) (ego y?))
				(< (Abs (- (ego priority?) 10)) 30)
			else
				0
			)
		)
	)
)

(instance poVultures of View
	(properties
		x 269
		y 369
		view 20104
		loop 4
	)
	
	(method (init)
		(MonoOut {using fake buzzard art})
		(super setPri: 40 nCurPosX: 56 init: &rest)
	)
)

(instance poLegOMeat of View
	(properties
		x 300
		y 300
		view 20308
	)
	
	(method (init)
		(MonoOut {using fake meat art})
		(super setPri: 40 nCurPosX: 56 init: &rest)
	)
)

(instance oBagpipe of TPSound
	(properties
		number -14435
	)
)

(instance voCrystcorder of View
	(properties
		view -5196
	)
	
	(method (init)
		(MonoOut {using fake cryst corder art})
		(if (not ((ScriptID 64017 0) test: 138))
			(= gGLastVerbX (ego x?))
			(= gGLastVerbY (ego y?))
			(= global236 (Max 20 (ego priority?)))
			((ScriptID 64017 0) set: 138)
			((ScriptID 56000 0) lThumbLoop:)
		)
		(super
			posn: gGLastVerbX gGLastVerbY
			setPri: global236
			nCurPosX: (+ 16 global236)
			init: &rest
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			((ScriptID 56000 0) stop:)
			(ego get: ((ScriptID 64001 0) get: 52))
			((ScriptID 64017 0) clear: 138)
			(self dispose:)
		)
	)
	
	(method (onMe &tmp temp0)
		(= temp0
			(if
				(and
					(super onMe: &rest)
					(super onMe: (ego x?) (ego y?))
				)
				(< (Abs (- (ego priority?) 10)) 30)
			else
				0
			)
		)
	)
)

(instance foAll of OpaqueFeature
	(properties)
)

(instance foVoid of Feature
	(properties
		x 474
		y 474
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 63 64)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(switch theVerb
			(63
				(oBagpipe play:)
				(= temp0 (- (ego x?) ((user curEvent?) x?)))
				(= temp1 (- (ego y?) ((user curEvent?) y?)))
				(= temp2
					(+ (MulDiv temp0 temp0 30) (MulDiv temp1 temp1 30))
				)
				(= local0 2)
				(if (> temp0 0) (= local1 3) else (= local1 -3))
				(if (> temp1 0) (= local2 3) else (= local2 -3))
				(if (< temp2 30)
					(if (> temp1 0)
						(= local3 (+ local3 2))
					else
						(= local3 (- local3 2))
					)
				)
			)
			(64
				(voCrystcorder init:)
				(ego put: ((ScriptID 64001 0) get: 52))
			)
		)
	)
	
	(method (onMe)
		(return 1)
	)
)

(instance poIntoVoid of Prop
	(properties
		x 243
		y 183
		view -14434
	)
)

(instance soIntoVoid of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(oNullVoidPlane makeTopBorder: 0 0)
				(poIntoVoid cel: 0 init: setCycle: End self setPri: 10)
			)
			(1 (self rememberMessage:))
		)
	)
	
	(method (rememberMessage)
		(ego posn: 244 195 show:)
		(poIntoVoid dispose:)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(curRoom setScript: self)
	)
)

(instance oNullVoidPlane of TorScrollPlane
	(properties
		priority 20
		oBoogleFeatures 0
		oExtraPlanes 0
		oMainPlane 0
		addBoogleFeature 0
	)
	
	(method (nSeq)
		(AddPicAt self -14436 0 0)
		(AddPicAt self -14435 474 0)
		(AddPicAt self -14434 0 316)
		(AddPicAt self -14433 474 316)
		(AddPicAt self -14432 0 632)
		(AddPicAt self -14431 474 632)
	)
)

(instance roInsideNullVoid of TPRoom
	(properties
		picture -14436
	)
	
	(method (init)
		(super init: &rest)
		(= plane (oNullVoidPlane init: 948 948 yourself:))
		(music1 pageSize: -14436)
		(foVoid init:)
		(foAll init:)
		(foLycentiaExit init:)
		(poVultures init:)
		(poLegOMeat init:)
		(theGame handsOn:)
		(ego
			init:
			oPanner:
			view: -14435
			hide:
			setMotion: 0
			setCycle: 0
		)
		(switch prevRoomNum
			(-14336
				(ego
					posn: (foLycentiaExit x?) (foLycentiaExit y?)
					setPri: 10
					show:
				)
				(= local1 -3)
				(= local2 -3)
				(= local3 0)
				(= local0 2)
			)
			(else 
				(self setScript: soIntoVoid)
				(= local0 (= local1 (= local2 (= local3 0))))
			)
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4)
		(super doit:)
		(if
			(and
				(ego isNotHidden:)
				(or (!= 0 local1) (!= 0 local2) (!= 0 local3))
			)
			(= temp0 (+ (ego x?) local1))
			(= temp1 (+ (ego y?) local2))
			(= temp2 (+ (ego priority?) local3))
			(= temp3 (localproc_0068 temp0 temp1))
			(if (< temp2 1) (= temp2 1))
			(if (> temp2 128) (= temp2 128))
			(if (> temp3 7400)
				(= local1 (- local1))
				(= local2 (- local2))
				(= local3 (- local3))
			else
				(ego
					setPri: temp2
					posn: temp0 temp1
					nCurPosX: (+ 16 temp2)
				)
				(UpdateScreenItem ego)
			)
		)
		(if (foLycentiaExit onMe:) (curRoom newRoom: -14336))
		(if (<= local0 0)
			(if (and (<= -1 local1) (<= local1 1))
				(= local1 0)
			else
				(= local1 (MulDiv local1 2 3))
			)
			(if (and (<= -1 local2) (<= local2 1))
				(= local2 0)
			else
				(= local2 (MulDiv local2 2 3))
			)
			(if (and (<= -1 local3) (<= local3 1))
				(= local3 0)
			else
				(= local3 (MulDiv local3 2 3))
			)
			(= local0 2)
		else
			(-- local0)
		)
		((ScriptID 64017 0) test: 138)
	)
	
	(method (setWander param1)
		(return (if (== param1 -14336) (return foLycentiaExit) else 0))
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 46))
		(ego get: ((ScriptID 64001 0) get: 52))
	)
)
