;;; Sierra Script 1.0 - (do not remove this comment)
(script# BAZAAR) ;51
(include game.sh)
(use Main)
(use Target)
(use Game)
(use Actor)
(use System)

(public
	bazaarR 0
	proc51_1 1
	proc51_2 2
	BazaarBuy 3
)

(procedure (proc51_1 &tmp temp0 temp1 temp2 temp3 i)
	(= temp0 0)
	(= temp2 999)
	(for ((= i 0)) (< i (cast size?)) ((++ i))
		(if
			(and
				((cast at: i) isKindOf: (if Actor else TargActor))
				(!= (= temp1 (cast at: i)) ego)
				(<
					(= temp3
						(GetDistance (ego x?) (ego y?) (temp1 x?) (temp1 y?))
					)
					temp2
				)
			)
			(= temp2 temp3)
			(= temp0 temp1)
		)
	)
	temp0
)

(procedure (proc51_2 theItem &tmp temp0)
	(return
		(==
			((= temp0 (inventory at: theItem)) realOwner?)
			curRoom
		)
	)
)

(procedure (BazaarBuy theItem howMany theVendor &tmp haveC haveR costC [str 40])
	(= haveR (inventory at: iRoyals))
	(= haveC (+ commons (* 100 (haveR amount?))))
	(if
		(<
			(= costC
				(*
					howMany
					(if (== 100 (((theVendor goods?) at: theItem) denomination?))
						(* 100 (((theVendor goods?) at: theItem) price?))
					else
						(((theVendor goods?) at: theItem) price?)
					)
				)
			)
			commons
		)
		(-= commons costC)
	else
		(-= haveC costC)
		((inventory at: iRoyals) amount: (/ haveC 100))
		(= commons (mod haveC 100))
	)
	(((theVendor goods?) at: theItem)
		quantity: (- (((theVendor goods?) at: theItem) quantity?) howMany)
	)
)

(instance bazaarR of Region
	
	(method (init)
		(if (and (not Night) (not (Btst 135)))
			(if (and (> Clock 2650) (not (Btst fEgoIsAsleep)))
				(Bclr fInMainGame)
			)
			(if (Btst fEgoIsAsleep)
				(Bset 121)
				(PalVary PALVARYPAUSE 1)
			)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(if
			(and
				(Btst fInMainGame)
				(not (Btst 135))
				(> Clock 2650)
				(not Night)
			)
			(Bclr fInMainGame)
		)
	)
	
	(method (dispose)
		(if (Btst 121)
			(Bclr 121)
			(PalVary PALVARYPAUSE 0)
		)
		(if (not (OneOf newRoomNum 230 240 250 260))
			(Bset fInMainGame)
			(DisposeScript BAZAAR)
		)
	)
	
	(method (newRoom n)
		(if (not (Btst fInMainGame))
			(if (or (== n 230) (== n 240) (== n 250) (== n 260))
				(Bset fVisitedBazaar)
				(cSound stop:)
			)
			(Bset fInMainGame)
		)
	)
)

(class MerchantActor of Actor
	(properties
		lastAnswerDay 0
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (newGreeting &tmp temp0)
	)
	
	(method (newRumor &tmp [temp0 2])
	)
	
	(method (newTellResponse)
	)
)
