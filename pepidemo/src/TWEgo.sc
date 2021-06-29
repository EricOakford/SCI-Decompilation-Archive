;;; Sierra Script 1.0 - (do not remove this comment)
(script# TWEGO)
(include game.sh) (include "0.shm")
(use Main)
(use StopWalk)
(use Grooper)
(use Ego)
(use System)

(public
	pepper 0
	lockjaw 1
)

(instance pepperStopWalk of StopWalk)

(instance lockjawStopWalk of StopWalk)

(instance pepperStopGroop of GradualLooper
	
	(method (init c)
		(= client c)
		(super init: &rest)
	)
	
	(method (doit)
		(if
			(and
				(OneOf ((ScriptID TWEGO 0) view?) 800 790)
				(== (client loop?) (- (NumLoops client) 1))
				(not (& (client signal?) fixedLoop))
			)
			(client loop: (client cel?))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= client 0)
		(super dispose:)
	)
)

(instance lockjawStopGroop of GradualLooper
	
	(method (init c)
		(= client c)
		(super init: &rest)
	)
	
	(method (doit)
		(if
			(and
				(== ((ScriptID TWEGO 1) view?) 807)
				(== (client loop?) (- (NumLoops client) 1))
				(not (& (client signal?) fixedLoop))
			)
			(client loop: (client cel?))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= client 0)
		(super dispose:)
	)
)

(class TWEgo of Ego
	(properties
		name {ego}
		sightAngle 40
		normal 0
	)
	
	(method (init param1 param2)
		(super init: &rest)
		(self normalize:)
	)
	
	(method (doit)
		(super doit: &rest)
	)
	
	(method (handleEvent event)
		(return
			(if (& (event type?) direction)
				(return FALSE)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (put)
		(super put: &rest)
		(theIconBar curIcon: (theIconBar walkIconItem?))
	)
	
	(method (normalize egoView egoLoop egoX egoY &tmp theView theLoop theX theY)
		(= theView view)
		(= theLoop 8)
		(= theX x)
		(= theY y)
		(cond 
			(argc
				(= theView egoView)
				(if (> argc 1)
					(= theLoop egoLoop)
					(if (> argc 2)
						(= theX egoX)
						(if (> argc 3) (= theY egoY))
					)
				)
			)
			((== self (ScriptID TWEGO 0))
				(if (Btst fPepperInBoyClothes)
					(= theView 790)
				else
					(= theView 800)
				))
			(else
				(= theView 807)
			)
		)
		(self
			view: theView
			setLoop: -1
			setLoop:
				(if (== self (ScriptID TWEGO 0))
					pepperStopGroop
				else
					lockjawStopGroop
				)
			setMotion: 0
			setCycle:
				(if (== self (ScriptID TWEGO 0))
					pepperStopWalk
				else
					lockjawStopWalk
				)
				-1
			setStep: 3 2
			illegalBits: 0
			ignoreHorizon:
			ignoreActors: FALSE
			setSpeed: speed
			posn: theX theY
			loop: theLoop
			signal: (| signal skipCheck)
			setLoop: -1
			setPri: -1
			state: (= state (| state startUpdOn))
			forceUpd:
		)
	)
)

(instance pepper of TWEgo
	(properties
		x 160
		y 100
		noun N_PEPPER
		modNum 0
		view 800
	)
)

(instance lockjaw of TWEgo
	(properties
		x 160
		y 100
		noun N_LOCKJAW
		modNum 0
		view 807
	)
	
	(method (init)
		(self setScript: (ScriptID LOCKJAW_STUFF 0))
		(super init: &rest)
	)
)
