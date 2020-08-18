;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWATINV)
(include game.sh)
(use Main)
(use Actor)
(use System)

(public
	swatInvInit 0
	DrawInvWindow 1
	addToInv 2
)

(procedure (DrawInvWindow &tmp temp0 temp1 inv temp3 temp4 temp5 temp6 temp7 obj temp9 temp10 temp11 newSet temp13 temp14 temp15 temp16 temp17)
	(= inv inventory)
	(= newSet (Set new:))
	(= temp13 0)
	(= temp5 (= temp4 (= temp1 0)))
	(= temp0 18)
	(= temp9 209)
	(= temp10 25)
	(= temp3 0)
	(while (< temp3 (inv size:))
		(= obj (inv at: temp3))
		(if (not (obj isKindOf: PQInvItem)) (break))
		(= temp6
			(CelWide (obj view?) (obj loop?) (obj cel?))
		)
		(= temp7
			(CelHigh (obj view?) (obj loop?) (obj cel?))
		)
		(if
			(and
				temp4
				(> (+ temp0 temp6 10) (+ (* temp9 temp5) 194))
			)
			(= temp16 (/ (- 176 temp13) (+ temp4 1)))
			(= temp17 (+ (* temp9 temp5) 18))
			(= temp15 0)
			(while (< temp15 (newSet size:))
				(= temp14 (newSet at: temp15))
				(temp14 x: (+ temp17 temp16))
				(= temp17
					(+
						temp17
						temp16
						(CelWide (temp14 view?) (temp14 loop?) (temp14 cel?))
					)
				)
				(++ temp15)
			)
			(= temp13 0)
			(newSet release:)
			(= temp4 0)
			(++ temp5)
		)
		(= temp13 (+ temp13 temp6))
		(if (not temp4)
			(= temp0 (* temp5 temp9))
			(obj x: (+ temp0 18))
			(= temp0 (+ temp0 18 temp6))
		else
			(obj x: (+ temp0 10))
			(= temp0 (+ temp0 10 temp6))
		)
		(obj y: (+ (/ (- temp10 temp7) 2) 2 (* 1 temp10)))
		(++ temp4)
		(newSet add: obj)
		(++ temp3)
	)
	(newSet release: dispose:)
	(theInterface
		numInvPages: temp5
		curInvPage: 0
		nextInvPage: 0
	)
)

(instance swatInvInit of Code
	
	(method (doit param1 &tmp temp0)
		(= temp0 global108)
		(if argc (= temp0 param1))
		(if ((= inventory theInventoryCast) size:)
			(inventory eachElementDo: #perform removeInvItems)
		)
		(inventory addToFront: mp5 FMJ9Mag flashBang govt45Light)
		(DrawInvWindow)
		(inventory eachElementDo: #init inventory)
	)
)

(instance addToInv of Code
	
	(method (doit param1 param2 &tmp temp0 temp1)
		(if
			(not
				(= temp0
					(switch param1
						(iGovt45 govt45)
						(iGovt45Light govt45Light)
						(iGasMask gasMask)
						(iMp5 mp5)
						(iGauge12 gauge12)
						(iGauge12Buck gauge12Buck)
						(iGauge12Slug gauge12Slug)
						(iJHP45Mag JHP45Mag)
						(iFMJ45Mag FMJ45Mag)
						(iSTHP9Mag STHP9Mag)
						(iFMJ9Mag FMJ9Mag)
						(iFlashBang flashBang)
						(iMirrorOnStick mirrorOnStick)
						(iBatteringRam batteringRam)
						(iRobar308 robar308)
						(iFederal308 federal308)
						(iGasGun gasGun)
						(iGasRound gasRound)
						(iGasGrenade gasGrenade)
						(iGhilleSuit ghilleSuit)
						(iDopeBook dopeBook)
						(else  0)
					)
				)
			)
			(return)
		)
		(if (or (< argc 2) (not param2))
			(inventory add: temp0)
		else
			(inventory delete: temp0)
		)
		(= temp1 (theInterface setInvPage: 0))
		(DrawInvWindow)
		(theInterface setInvPage: temp1)
		(if (!= (theInterface numInvPages?) temp1)
			(theInterface
				nextInvPage: (- temp1 (theInterface numInvPages?))
			)
		)
		(UpdatePlane theInventoryPlane)
	)
)

(class GunData of Object
	(properties
		numRounds 0
		roundsLeft 0
		storeExtra 0
		ownedBy 0
		evtHandler 0
	)
	
	(method (new param1 &tmp newSuper temp1)
		(if (= newSuper (super new:))
			(= temp1 0)
			(newSuper ownedBy: [param1 0])
			(if (>= argc 2)
				(newSuper numRounds: [param1 1])
				(= temp1 [param1 1])
				(if (>= argc 3)
					(newSuper storeExtra: [param1 2])
					(= temp1 (+ temp1 [param1 2]))
				)
			)
			(newSuper roundsLeft: temp1)
		)
		(return newSuper)
	)
	
	(method (dispose param1)
		(if (!= ownedBy param1) (return))
		(super dispose:)
	)
)

(class PQInvItem of View
	(properties
		verb 1
		owner 0
		newX 0
		newY 0
		cView 0
		cLoop 0
		cCel 0
		enabled 0
		gunData 0
	)
	
	(method (init)
		(self setPri: 50 ignoreActors: 1 enable: 1)
		(super init: &rest)
	)
	
	(method (dispose)
		(if gunData (gunData dispose: self))
		(super dispose:)
	)
	
	(method (show)
		(if newX (= x newX) (= y newY) (= newX (= newY 0)))
		(super show:)
	)
	
	(method (doVerb theVerb)
		(return
			(if (and enabled (== theVerb 29))
				(self hide:)
				(theInterface setInvItem: self)
			else
				(theGame pragmaFail:)
				(return 1)
			)
		)
	)
	
	(method (onMe)
		(return
			(if (Btst 4)
				(return (super onMe: &rest))
			else
				(return 0)
			)
		)
	)
	
	(method (isMyVerb param1)
		(return (== verb param1))
	)
	
	(method (moveTo theOwner)
		(= owner theOwner)
		(return self)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (enable param1)
		(if (and argc (not param1))
			(= enabled 0)
		else
			(= enabled 1)
		)
	)
)

(instance govt45 of PQInvItem
	(properties
		view 22
		verb 6
		cView 22
		cLoop 1
	)
	
	(method (init)
		(super init: &rest)
		((= gunData (GunData new: self 8 1))
			evtHandler: EHGovt45
		)
	)
)

(instance govt45Light of PQInvItem
	(properties
		view 23
		verb 6
		cView 23
		cLoop 1
	)
	
	(method (init)
		(super init: &rest)
		((= gunData (GunData new: self 8 1))
			evtHandler: EHGovt45
		)
	)
)

(instance gasMask of PQInvItem
	(properties
		view 35
		verb 14
		cView 35
		cLoop 1
	)
)

(instance mp5 of PQInvItem
	(properties
		view 25
		verb 7
		cView 25
		cLoop 1
	)
	
	(method (init)
		(super init: &rest)
		((= gunData (GunData new: self 30)) evtHandler: EHMP5)
	)
)

(instance gauge12 of PQInvItem
	(properties
		view 26
		verb 37
		cView 26
		cLoop 1
	)
	
	(method (init)
		(super init: &rest)
		((= gunData (GunData new: self 7 1))
			evtHandler: EH12Guage
		)
	)
)

(instance gauge12Buck of PQInvItem
	(properties
		view 27
		verb 38
		cView 27
		cLoop 1
	)
)

(instance gauge12Slug of PQInvItem
	(properties
		view 28
		verb 39
		cView 28
		cLoop 1
	)
)

(instance JHP45Mag of PQInvItem
	(properties
		view 29
		verb 35
		cView 29
		cLoop 2
	)
)

(instance FMJ45Mag of PQInvItem
	(properties
		view 30
		verb 36
		cView 30
		cLoop 2
	)
)

(instance STHP9Mag of PQInvItem
	(properties
		view 31
		verb 52
		cView 31
		cLoop 2
	)
)

(instance FMJ9Mag of PQInvItem
	(properties
		view 32
		verb 53
		cView 32
		cLoop 2
	)
)

(instance flashBang of PQInvItem
	(properties
		view 34
		verb 16
		cView 34
		cLoop 1
	)
)

(instance mirrorOnStick of PQInvItem
	(properties
		view 36
		verb 40
		cView 36
		cLoop 1
	)
)

(instance batteringRam of PQInvItem
	(properties
		view 37
		verb 41
		cView 37
		cLoop 1
	)
)

(instance robar308 of PQInvItem
	(properties
		view 38
		verb 25
		cView 38
		cLoop 1
	)
	
	(method (init)
		(super init: &rest)
		((= gunData (GunData new: self 5))
			evtHandler: EHRobar308
		)
	)
)

(instance federal308 of PQInvItem
	(properties
		view 40
		verb 27
		cView 40
		cLoop 1
	)
)

(instance gasGun of PQInvItem
	(properties
		view 42
		verb 28
		cView 42
		cLoop 1
	)
)

(instance gasRound of PQInvItem
	(properties
		view 43
		verb 31
		cView 43
		cLoop 1
	)
)

(instance gasGrenade of PQInvItem
	(properties
		view 44
		verb 32
		cView 44
		cLoop 1
	)
)

(instance ghilleSuit of PQInvItem
	(properties
		view 45
		verb 33
		cView 45
		cLoop 1
	)
)

(instance dopeBook of PQInvItem
	(properties
		view 46
		verb 34
		cView 46
		cLoop 1
	)
)

(instance EHGovt45 of EventHandler
	(properties)
	
	(method (handleEvent event)
		(event claimed: 0)
	)
)

(instance EHMP5 of EventHandler
	(properties)
	
	(method (handleEvent event)
		(event claimed: 0)
	)
)

(instance EH12Guage of EventHandler
	
	(method (handleEvent event)
		(event claimed: 0)
	)
)

(instance EHRobar308 of EventHandler
	
	(method (handleEvent event)
		(event claimed: 0)
	)
)

(instance EHRobar223 of EventHandler
	
	(method (handleEvent event)
		(event claimed: 0)
	)
)

(instance removeInvItems of Code
	
	(method (doit obj)
		(if (obj isKindOf: PQInvItem)
			(inventory delete: obj)
		)
	)
)
