;;; Sierra Script 1.0 - (do not remove this comment)
(script# 940)
(include game.sh)
(use Main)
(use Intrface)
(use Dialog)

;;;(public
;;;	PrintD 0
;;;)

;NOTE: This entire module has been commented out,
; as it is obsolete as of SCI1.1.

;;;(procedure (PrintD param1 &tmp temp0 newDText newClass_255_0 newClass_255_0NsRight temp4 newClass_255_0NsBottom temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14)
;;;	(= temp10 (= temp11 -1))
;;;	(= newClass_255_0NsRight
;;;		(= temp4 (= newClass_255_0NsBottom (= temp6 0)))
;;;	)
;;;	(= temp12 0)
;;;	(= temp14 0)
;;;	((= newDText (DText new:)) window: systemWindow)
;;;	(= temp0 0)
;;;	(while (< temp0 argc)
;;;		(switch (= temp8 [param1 temp0])
;;;			(109
;;;				(= newClass_255_0NsBottom (newClass_255_0 nsBottom?))
;;;				(= newClass_255_0NsRight 0)
;;;			)
;;;			(64
;;;				(= temp10 [param1 (++ temp0)])
;;;				(= temp11 [param1 (++ temp0)])
;;;			)
;;;			(77
;;;				(= temp12 [param1 (++ temp0)])
;;;			)
;;;			(124
;;;				(= temp14 [param1 (++ temp0)])
;;;			)
;;;			(else 
;;;				(++ temp0)
;;;				(switch temp8
;;;					(23
;;;						((= newClass_255_0 (Class_255_0 new:))
;;;							text: [param1 temp0]
;;;						)
;;;					)
;;;					(78
;;;						((= newClass_255_0 (DIcon new:))
;;;							text: [param1 temp0]
;;;							value: [param1 (++ temp0)]
;;;						)
;;;					)
;;;					(79
;;;						((= newClass_255_0 (Dialog new:))
;;;							view: [param1 temp0]
;;;							loop: [param1 (++ temp0)]
;;;							cel: [param1 (++ temp0)]
;;;						)
;;;					)
;;;					(38
;;;						((= newClass_255_0 (DButton new:))
;;;							text: [param1 temp0]
;;;							max: [param1 (++ temp0)]
;;;						)
;;;					)
;;;					(else 
;;;						((= newClass_255_0 (Class_255_0 new:))
;;;							text: [param1 (-- temp0)]
;;;						)
;;;					)
;;;				)
;;;				(if
;;;				(and (< (+ temp0 1) argc) (== [param1 (+ temp0 1)] 1))
;;;					(++ temp0)
;;;					(= newClass_255_0NsRight
;;;						(+ newClass_255_0NsRight [param1 (++ temp0)])
;;;					)
;;;				)
;;;				(if
;;;				(and (< (+ temp0 1) argc) (== [param1 (+ temp0 1)] 0))
;;;					(++ temp0)
;;;					(= newClass_255_0NsBottom
;;;						(+ newClass_255_0NsBottom [param1 (++ temp0)])
;;;					)
;;;				)
;;;				(newClass_255_0
;;;					check:
;;;					playBed: (+ newClass_255_0NsRight 4) (+ newClass_255_0NsBottom 4)
;;;				)
;;;				(newDText add: newClass_255_0)
;;;				(= newClass_255_0NsRight (newClass_255_0 nsRight?))
;;;			)
;;;		)
;;;		(++ temp0)
;;;	)
;;;	(newDText check: cycle:)
;;;	(newDText
;;;		playBed:
;;;			(if (== -1 temp10) (newDText nsLeft?) else temp10)
;;;			(if (== -1 temp11) (newDText nsTop?) else temp11)
;;;	)
;;;	(if temp12 (newDText text: temp12))
;;;	(= temp13 (newDText at: temp14))
;;;	(if (not (& $0001 (temp13 state?))) (= temp13 0))
;;;	(= temp7
;;;		(newDText setMark: (if temp12 4 else 0) -1 doit: temp13)
;;;	)
;;;	(if (IsObject temp7)
;;;		(if (temp7 isKindOf: DIcon)
;;;			(= temp7 (temp7 value?))
;;;		else
;;;			(= temp7 1)
;;;		)
;;;	)
;;;	(newDText dispose:)
;;;	(return temp7)
;;;)
;;;