;;; Sierra Script 1.0 - (do not remove this comment)
(script# LOCKER)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	LockerScript 0
)

(local
	[num1Buf 20]
	[num2Buf 20]
	[num3Buf 20]
)
(instance LockerScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fLearnedLockerCombination)
					(self cue:)
				else
					(theGame changeScore: 65)
					(Print 45 0 #icon 9 0 1)
					(= seconds 2)
				)
			)
			(1
				;when learning the locker combination, the game randomly
				;decides the manual's page numbers listed in TEXT.046.
				(if (Btst fLearnedLockerCombination)
					(Format @num1Buf 45 1 46 lockerNum1)
					(Format @num2Buf 45 1 46 lockerNum2)
					(Format @num3Buf 45 1 46 lockerNum3)
					(Printf 45 2 @num1Buf @num2Buf @num3Buf)
				else
					(Bset fLearnedLockerCombination)
					(Format @num1Buf 45 1 46 (= lockerNum1 0))
					(while (== 32 (StrAt @num1Buf 0))
						(= lockerNum1 (Random 1 24))
						(Format @num1Buf 45 1 46 lockerNum1)
					)
					(= lockerNum2 lockerNum1)
					(while
						(or
							(== lockerNum1 lockerNum2)
							(== 32 (StrAt @num2Buf 0))
						)
						(= lockerNum2 (Random 1 24))
						(Format @num2Buf 45 1 46 lockerNum2)
					)
					(= lockerNum3 lockerNum1)
					(while
						(or
							(== lockerNum1 lockerNum3)
							(== lockerNum2 lockerNum3)
							(== 32 (StrAt @num3Buf 0))
						)
						(= lockerNum3 (Random 1 24))
						(Format @num3Buf 45 1 46 lockerNum3)
					)
					(Printf 45 3 @num1Buf @num2Buf @num3Buf)
				)
				(HandsOn)
				(theGame setScript: 0)
				(DisposeScript LOCKER)
				(DisposeScript PAGENUMS)
			)
		)
	)
)
