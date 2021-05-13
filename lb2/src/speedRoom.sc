;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include sci.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	speedRoom 0
)

(local
	local0
	local1
)
(instance speedRoom of Rm
	(properties
		picture 780
	)
	
	(method (init)
		(LoadMany 128 104)
		(super init:)
		(theGame handsOff:)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(= temp0 0)
		(while (< temp0 500)
			(++ temp0)
		)
	)
)

(instance fred of Actor
	(properties
		view 104
	)
)

(instance speedTest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fred
					setLoop: 0
					illegalBits: 0
					posn: 0 0
					setStep: 1 1
					setCycle: Fwd
					init:
				)
				(= cycles 1)
			)
			(1
				(= local1 (GetTime))
				(fred setMotion: MoveTo 320 190)
				(= cycles 50)
			)
			(2
				(= local0 (- (GetTime) local1))
				(startGame doit:)
			)
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit &tmp [temp0 100])
		(cond 
			((> local0 160) (= howFast 0))
			((> local0 150) (= howFast 1))
			((> local0 140) (= howFast 2))
			((> local0 130) (= howFast 3))
			((> local0 120) (= howFast 4))
			((> local0 110) (= howFast 5))
			((> local0 100) (= howFast 6))
			((> local0 90) (= howFast 7))
			((> local0 80) (= howFast 8))
			((> local0 70) (= howFast 9))
			((> local0 60) (= howFast 10))
			((> local0 50) (= howFast 11))
			((> local0 40) (= howFast 12))
			((> local0 30) (= howFast 13))
			((> local0 20) (= howFast 14))
			(else (= howFast 15))
		)
		(theGame
			detailLevel: (cond 
				((<= howFast 3) 1)
				((<= howFast 10) 2)
				(else 3)
			)
		)
		(= eatMice 2)
		(curRoom newRoom: (if debugging 29 else 105))
	)
)
