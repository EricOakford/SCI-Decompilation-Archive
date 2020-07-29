;;; Sierra Script 1.0 - (do not remove this comment)
(script# 940)
(include sci.sh)
(use Main)
(use Intrface)

(public
	PrintD 0
)

(procedure (PrintD param1 &tmp temp0 newDialog newDText newDTextNsRight temp4 newDTextNsBottom temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14)
	(= temp10 (= temp11 -1))
	(= newDTextNsRight
		(= temp4 (= newDTextNsBottom (= temp6 0)))
	)
	(= temp12 0)
	(= temp14 0)
	((= newDialog (Dialog new:)) window: systemWindow)
	(= temp0 0)
	(while (< temp0 argc)
		(switch (= temp8 [param1 temp0])
			(106
				(= newDTextNsBottom (newDText nsBottom?))
				(= newDTextNsRight 0)
			)
			(67
				(= temp10 [param1 (++ temp0)])
				(= temp11 [param1 (++ temp0)])
			)
			(80
				(= temp12 [param1 (++ temp0)])
			)
			(121
				(= temp14 [param1 (++ temp0)])
			)
			(else 
				(++ temp0)
				(switch temp8
					(26
						((= newDText (DText new:)) text: [param1 temp0])
					)
					(81
						((= newDText (DButton new:))
							text: [param1 temp0]
							value: [param1 (++ temp0)]
						)
					)
					(82
						((= newDText (DIcon new:))
							view: [param1 temp0]
							loop: [param1 (++ temp0)]
							cel: [param1 (++ temp0)]
						)
					)
					(41
						((= newDText (DEdit new:))
							text: [param1 temp0]
							max: [param1 (++ temp0)]
						)
					)
					(else 
						((= newDText (DText new:)) text: [param1 (-- temp0)])
					)
				)
				(if
				(and (< (+ temp0 1) argc) (== [param1 (+ temp0 1)] 4))
					(++ temp0)
					(= newDTextNsRight
						(+ newDTextNsRight [param1 (++ temp0)])
					)
				)
				(if
				(and (< (+ temp0 1) argc) (== [param1 (+ temp0 1)] 3))
					(++ temp0)
					(= newDTextNsBottom
						(+ newDTextNsBottom [param1 (++ temp0)])
					)
				)
				(newDText
					setSize:
					moveTo: (+ newDTextNsRight 4) (+ newDTextNsBottom 4)
				)
				(newDialog add: newDText)
				(= newDTextNsRight (newDText nsRight?))
			)
		)
		(++ temp0)
	)
	(newDialog setSize: center:)
	(newDialog
		moveTo:
			(if (== -1 temp10) (newDialog nsLeft?) else temp10)
			(if (== -1 temp11) (newDialog nsTop?) else temp11)
	)
	(if temp12 (newDialog text: temp12))
	(= temp13 (newDialog at: temp14))
	(if (not (& $0001 (temp13 state?))) (= temp13 0))
	(= temp7
		(newDialog open: (if temp12 4 else 0) -1 doit: temp13)
	)
	(if (IsObject temp7)
		(if (temp7 isKindOf: DButton)
			(= temp7 (temp7 value?))
		else
			(= temp7 1)
		)
	)
	(newDialog dispose:)
	(return temp7)
)
