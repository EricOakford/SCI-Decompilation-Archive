;;; Sierra Script 1.0 - (do not remove this comment)
(script# 367)
(include game.sh)
(use Main)
(use Intrface)
(use Submarine_806)
(use System)

(public
	damageScript 0
)

(instance damageScript of Script

	(method (init)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(if modelessDialog (modelessDialog dispose:))
		(switch (= state newState)
			(0
				(if (& (Submarine flags?) $0004)
					(Print 367 0
						#title {SONAR}
						#at 10 10
						#width 300
						#font 30
						#dispose
					)
				else
					(Print 367 1
						#title {SONAR}
						#at 10 10
						#width 300
						#font 30
						#dispose
					)
				)
				(= seconds 5)
			)
			(1
				(cond 
					((& (Submarine flags?) $0002)
						(Print 367 2
							#title {RADIO}
							#at 10 10
							#width 300
							#font 30
							#dispose
						)
					)
					((& (Submarine flags?) $0001)
						(Print 367 3
							#title {RADIO}
							#at 10 10
							#width 300
							#font 30
							#dispose
						)
					)
					(else
						(Print 367 1
							#title {RADIO}
							#at 10 10
							#width 300
							#font 30
							#dispose
						)
					)
				)
				(= seconds 5)
			)
			(2
				(Print 367 1
					#title {TORPEDO}
					#at 10 10
					#width 300
					#font 30
					#dispose
				)
				(= seconds 5)
			)
			(3
				(Print 367 1
					#title {MACHINERY}
					#at 10 10
					#width 300
					#font 30
					#dispose
				)
				(= seconds 5)
			)
			(4
				(Print 367 1
					#title {ENGINE}
					#at 10 10
					#width 300
					#font 30
					#dispose
				)
				(= seconds 5)
			)
			(5
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(self dispose:)
				(DisposeScript 367)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(& (event type?) mouseDown)
				(& (event type?) keyDown)
			)
			(self seconds: 0 cue:)
		)
	)
)
