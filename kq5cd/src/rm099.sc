;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm099 0
)

(local
	local0
	local1
)
(instance rm099 of Rm
	(properties
		picture 55
	)
	
	(method (init)
		(super init:)
		(= global327 0)
		(theGame setSpeed: 0)
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
	(properties)
)

(instance speedTest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 99)
				(= cycles 1)
			)
			(1
				(= local1 (GetTime))
				(fred
					view: 99
					setLoop: 0
					illegalBits: 0
					posn: 20 99
					setStep: 1 1
					setCycle: Fwd
					init:
					setMotion: MoveTo 100 100 self
				)
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
	
	(method (doit)
		(= howFast
			(cond 
				((> local0 600) 0)
				((> local0 550) 1)
				((> local0 500) 2)
				((> local0 450) 3)
				((> local0 400) 4)
				((> local0 350) 5)
				((> local0 300) 6)
				((> local0 275) 7)
				((> local0 250) 8)
				((> local0 225) 9)
				((> local0 200) 10)
				((> local0 100) 11)
				((> local0 60) 12)
				((> local0 40) 13)
				((> local0 20) 14)
				(else 15)
			)
		)
		(if
			(or
				(DoAvoider)
				(<= howFast 5)
				(and global400 (<= howFast 7))
			)
			(Bset 114)
		)
		(= howFast
			(cond 
				((> howFast 11) 2)
				((< howFast 4) 0)
				(else 1)
			)
		)
		(theGame
			detailLevel:
			(switch howFast
				(0 0)
				(1 2)
				(else  3)
			)
		)
		(User canControl: 1 canInput: 1)
		(theIconBar enable:)
		(= isHandsOff 0)
		(theGame setSpeed: 2)
		(Palette 4 0 255 100)
		(= eatMice aniInterval)
		(cond 
			((and (not global327) debugging) (DebugTP))
			(gPolyList15 (ego get: 28) (curRoom newRoom: 1))
			(else (= global287 (GetTime)) (curRoom newRoom: 119))
		)
	)
)
