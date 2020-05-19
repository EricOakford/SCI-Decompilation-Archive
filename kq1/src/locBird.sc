;;; Sierra Script 1.0 - (do not remove this comment)
(script# BIRD)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	locBird 0
)

(instance locBird of Locale
	(properties)
	
	(method (init)
		(if (OneOf curRoomNum 56 57 58 59 60 61 62 72 82)
			(Load VIEW 305)
		else
			(Load VIEW 301)
		)
		(super init: &rest)
		(birdie
			view:
			(if (OneOf curRoomNum 56 57 58 59 60 61 62 72 82)
				305
			else
				301
			)
			init:
			hide:
			illegalBits: 0
			ignoreActors:
			ignoreHorizon:
			setPri: 9
			setScript: flyBy
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check,listen/bird')
				(Print 611 0)
			)
			((Said 'throw,throw/boulder,pebble,pebble/bird')
				(Print 611 1)
			)
			((Said '/bird')
				(Print 611 2)
			)
		)
	)
)

(instance birdie of Actor
	(properties
		x -18
		signal $0000
		illegalBits $0000
		xStep 6
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((& signal actorHidden)
				(return)
			)
			((Said 'look,check/bird')
				(self doVerb: verbLook)
			)
			((Said 'get,take,capture/bird')
				(self doVerb: verbGet)
			)
			((Said 'kill/bird')
				(Print 611 5)
			)
			((Said 'feed/bird')
				(Print 611 6)
			)
			((Said '/bird')
				(Print 611 7)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 611 3)
			)
			(verbGet
				(Print 611 4)
			)
		)
	)
)

(instance flyBy of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= seconds (Random 4 9))
			)
			(1
				(= temp0 (Random 0 1))
				(birdie
					view:
					(if (OneOf curRoomNum 56 57 58 59 60 61 62 72 82)
						305
					else
						301
					)
					show:
					setLoop: (+ (if (Random 0 1) 2 else 0) temp0)
					setCycle: Forward
					posn: (if temp0 338 else -18) (Random 20 48)
					setMotion: MoveTo (if temp0 -18 else 338) (Random 20 48) self
				)
			)
			(2
				(birdie hide:)
				(= seconds (Random 6 14))
			)
			(3 (self changeState: 0))
		)
	)
)
