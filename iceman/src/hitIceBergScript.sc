;;; Sierra Script 1.0 - (do not remove this comment)
(script# 364)
(include game.sh)
(use Main)
(use Intrface)
(use Submarine_806)
(use EgoDead)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	hitIceBergScript 0
	slider 1
)

(local
	local0
	local1 = [125 185 135 175 185 125 165 145]
	theSubmarinePitch
	local10
	local11 =  20
	local12
	local13
	local14
	local15
)
(procedure (localproc_07b2 &tmp submarineHSpeed)
	(= submarineHSpeed
		(CosMult
			(Submarine _absHeading?)
			(if (> (= submarineHSpeed (Submarine hSpeed?)) 10)
				(/ submarineHSpeed 2)
			else
				submarineHSpeed
			)
		)
	)
	(if (< -10000 local12)
		(= local12 (+ local12 submarineHSpeed))
	)
)

(instance iceBergSound of Sound
	(properties
		number 20
		priority 11
	)
)

(instance hitIceBergScript of Script
	
	(method (init)
		(iceBerg init: setScript: iceBergScript)
		(= local11 (+ 5 (* 7 howFast)))
		(globalSound number: 90 loop: -1 play:)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(if local14 (self dispose:) (return))
		(= state newState)
		(if state
			(cond 
				((> (Submarine hSpeed?) 15)
					(= local15 1)
					(client setScript: gameOverScript 0 0)
				)
				((>= state 4)
					(= local15 1)
					(client setScript: gameOverScript 0 1)
				)
				((== state 3)
					(Submarine flags: (| (Submarine flags?) $0002))
					((ScriptID 27 1) setCel: 3 setPri: 15)
					(iceBergSound number: (SoundFX 20) play:)
				)
				(else
					(iceBergSound number: (SoundFX 20) play:)
					(Submarine
						flags: (| (Submarine flags?) (<< $0001 (- state 1)))
					)
				)
			)
			(ShakeScreen 10 3)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and (Said 'access/damage') (not script))
				(self setScript: (ScriptID 367))
			)
		)
	)
)

(instance gameOverScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(iceBergSound number: (SoundFX 49) play:)
				(ShakeScreen 10 3)
				(= seconds 5)
			)
			(1
				(if (== register 0)
					(EgoDead 926 4 0 364 0)
				else
					(Print 364 1)
					(Print 364 2)
					(EgoDead 926 2 0 364 3)
				)
			)
		)
	)
)

(instance iceBerg of Actor
	(properties
		y 51
		x 155
		yStep 1
		view 527
		loop 4
		priority 2
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
		xStep 1
	)
	
	(method (doit)
		(if (and (< 82 x) (< x 231))
			(super doit:)
		else
			(self dispose:)
		)
	)
)

(instance slider of Actor
	(properties
		y 63
		x 157
		yStep 1
		view 527
		loop 3
		cel 1
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
		moveSpeed 2
	)
	
	(method (doit &tmp submarinePitch)
		(if
			(!=
				(= submarinePitch (Submarine pitch?))
				theSubmarinePitch
			)
			(= theSubmarinePitch submarinePitch)
			(self
				setMotion: MoveTo 157 (- 63 (/ submarinePitch 10)) self
			)
		)
		(if (not (umod (++ local10) local11))
			(localproc_07b2)
			(++ local13)
		)
		(if
			(and
				(< 350 local12)
				(not (bergsOverScript client?))
				(>= local0 20)
			)
			(hitIceBergScript
				setScript: bergsOverScript hitIceBergScript
			)
		)
		(super doit:)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance iceBergScript of Script

	(method (changeState newState &tmp temp0 temp1)
		(= state newState)
		(client
			cycleSpeed: (/ (- 60 (Submarine hSpeed?)) 6)
			moveSpeed:
				(if (<= (Abs (Submarine rudder?)) 3)
				else
					(<= (client cel?) 3)
				)
			setPri: (if (< (client cel?) 2) 2 else (client cel?))
		)
		(if (not state)
			(client
				posn: [local1 (mod (++ local0) 8)] (- (slider y?) 12)
				setMotion: (Clone rudderMover) (client x?) (- (slider y?) 16)
				cycleSpeed: (/ (- 60 (Submarine hSpeed?)) 6)
				setCycle: (Clone speedCycler)
			)
			(if (not (client cycleSpeed?)) (client cycleSpeed: 10))
		)
		(if
			(and
				(== (client cel?) 5)
				(not register)
				(not (bergsOverScript client?))
			)
			(= register 1)
			((Clone client)
				init:
				script: 0
				mover: 0
				cycler: 0
				cel: 0
				setScript: ((Clone self) register: 0 yourself:)
				yourself:
			)
			(= temp1 ((client mover?) x?))
			(client setMotion: (Clone rudderMover) temp1 21)
		)
		(= cycles (client cycleSpeed?))
	)
)

(instance rudderMover of MoveTo

	(method (doit &tmp temp0 temp1 temp2)
		(= temp0
			(-
				(client x?)
				(*
					10
					(sign (Submarine rudder?))
					(sign (Submarine hSpeed?))
				)
			)
		)
		(if
			(or
				(!=
					(= temp1
						(if (> (Submarine _depth?) 125)
							(- (slider y?) (+ 13 (* (client cel?) 2)))
						else
							(- (slider y?) (+ 13 (client cel?)))
						)
					)
					y
				)
				(!= temp0 x)
			)
			(= temp2 ((client cycler?) cycleCnt?))
			(self init: client temp0 temp1)
			((client cycler?) cycleCnt: temp2)
		)
		(super doit:)
	)
	
	(method (moveDone)
	)
)

(instance speedCycler of EndLoop

	(method (doit)
		(= endCel
			(if
			(== (= cycleDir (sign (Submarine hSpeed?))) -1)
				0
			else
				(client lastCel:)
			)
		)
		(super doit:)
	)
	
	(method (cycleDone)
		(if
			(<
				(Abs (- 155 (client x?)))
				(/ (- 240 (Submarine depth:)) 3)
			)
			(curRoom cue:)
		)
		(client dispose:)
	)
)

(instance bergsOverScript of Script
	(properties
		cycles 200
	)
	
	(method (dispose &tmp temp0)
		(if local15 (return))
		(super dispose:)
		(= temp0
			(-
				10
				(+ (/ local13 100) (* 2 (hitIceBergScript state?)))
			)
		)
		(Printf 364 4 temp0)
		(theGame changeScore: temp0)
		(DisposeScript 364)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(globalSound fade:)
				(slider dispose:)
				(iceBerg dispose:)
				(iceBergSound dispose:)
				(= seconds 5)
			)
			(2
				(= local14 1)
				(self dispose:)
			)
		)
	)
)
