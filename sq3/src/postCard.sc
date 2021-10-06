;;; Sierra Script 1.0 - (do not remove this comment)
(script# 471)
(include game.sh)
(use Main)
(use Intrface)
(use Actor)
(use System)

(public
	postCard 0
)

(instance postCard of Code
	(method (init)
		(Load VIEW 73)
		(super init:)
	)
	
	(method (doit)
		(switch thePostcard
			(1
				(Print 471 0
					#icon 73 0 2
					#button {Read Back} 1
					#width 100
				)
				(Print 471 1 #font 999 #width 225)
			)
			(2
				(Print 471 0
					#icon 73 1 1
					#button {Read Back} 1
					#width 100
				)
				(Print 471 2 #font 999 #width 225)
			)
			(3
				(Print 471 0
					#icon 73 0 0
					#button {Read Back} 1
					#width 100
				)
				(Print 471 3 #font 999 #width 225)
			)
			(4
				(Print 471 0
					#icon 73 0 3
					#button {Read Back} 1
					#width 100
				)
				(Print 471 4 #font 999 #width 225)
			)
			(5
				(Print 471 0
					#icon 73 1 0
					#button {Read Back} 1
					#width 100
				)
				(Print 471 5 #font 999 #width 225)
			)
			(6
				(Print 471 0
					#icon 73 0 1
					#button {Read Back} 1
					#width 100
				)
				(Print 471 6 #font 999 #width 225)
			)
		)
		(DisposeScript 471)
	)
)

(instance card of Prop
	(method (init)
		(super init:)
		(self
			view: 73
			setLoop: 0
			setCel: 1
			setPri: 15
			posn: 100 53
		)
	)
)
