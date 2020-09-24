;;; Sierra Script 1.0 - (do not remove this comment)
(script# rSky)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm80 0
)

(local
	local0
)
(instance rm80 of Room
	(properties
		picture rSky
		style WIPELEFT
	)
	
	(method (init)
		(LoadMany VIEW
			vCondor
			vEgoFlown
			vEgoFall
		)
		(Load SOUND 17)
		(super init:)
		(condor init: x: -20 y: 130 setCycle: Forward illegalBits: 0)
		(HandsOff)
		(ego
			init:
			posn: -10 180
			view: vEgoFlown
			loop: loopE
			setCycle: (if (!= howFast slow) Forward else 0)
			setMotion: 0
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			illegalBits: 0
			setPri: 7
		)
		(condor setScript: flyThru)
	)
)

(instance flyThru of Script

	(method (doit)
		(super doit:)
		(cond 
			((and (> (ego x?) 170) (not (curRoom script?)))
				(birdRide dispose:)
				(curRoom setScript: fallingEgo)
			)
			((and (> (condor x?) 5) (not local0))
				(= local0 1)
				(DisplayNewGraphics)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 80 0
					#at 25 20
					#width 260
					#mode teJustCenter
					#dispose
				)
				(self setScript: birdRide)
				(condor setMotion: MoveTo 350 10)
			)
		)
	)
)

(instance birdRide of Script

	(method (doit)
		(super doit: &rest)
		(ego posn: (condor x?) (+ (condor y?) 60))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: vEgoFlown
					loop: 0
					setCycle: (if (!= howFast slow) Forward else 0)
					setMotion: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
					illegalBits: 0
					setPri: 7
				)
			)
		)
	)
)

(instance fallingEgo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 6) number: 17 loop: -1 play:)
				(cls)
				(ego
					view: vEgoFall
					setLoop: 0
					xStep: 10000
					setMotion: JumpTo 182 245 self
				)
			)
			(1
				(curRoom newRoom: dmGiant)
			)
		)
	)
)

(instance condor of Actor
	(properties
		view 160
		cel 3
		priority 8
		signal fixPriOn
	)
)
