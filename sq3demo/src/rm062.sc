;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	rm062 0
)

(instance rm062 of Room
	(properties
		picture 62
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 63)
		(Load SOUND 17)
		(super init:)
		(theMusic number: 17 loop: -1 play:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if (not (-- shakeTimer))
			(ShakeScreen 5 3)
			(= shakeTimer (Random 100 200))
		)
		(super doit:)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= shakeTimer 30)
				(HandsOff)
				(ego
					setStep: 3 3
					view: 63
					setLoop: 0
					posn: 164 142
					illegalBits: 0
					setPri: -1
					init:
				)
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 151 162 self)
			)
			(2
				(curRoom setScript: demoScript)
			)
		)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 146 188 self)
			)
			(1
				(= temp0 (Print 62 0 #at -1 20 #width 280 #dispose))
				(= seconds 20)
			)
			(2 (cls) (theGame restart:))
		)
	)
)
