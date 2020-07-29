;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Actor)
(use System)

(public
	rm415 0
)

(local
	local0
)
(instance rm415 of LLRoom
	(properties
		picture 415
	)
	
	(method (init)
		(LoadMany VIEW 1415 1416)
		(super init:)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(theMusic fade: 0 15 12 1 self)
		(super dispose:)
	)
)

(instance You of Talker
	(properties
		x 160
		y 180
		nsTop 76
		nsLeft 168
		view 1416
		loop 3
	)
	
	(method (init)
		(= mouth pattiMouth)
		(super init: &rest)
	)
)

(instance pattiMouth of Prop
	(properties
		nsLeft 1
		view 1416
		cycleSpeed 5
	)
)

(instance Inspector_Desmond of Talker
	(properties
		x 1
		y 180
		nsTop 73
		nsLeft 120
		view 1415
		loop 3
		talkWidth 160
		name "Inspector Desmond"
	)
	
	(method (init)
		(= mouth desmondMouth)
		(super init: &rest)
	)
)

(instance desmondMouth of Prop
	(properties
		view 1415
		cycleSpeed 5
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say Inspector_Desmond 415 0 #dispose #caller self)
			)
			(1 (= ticks 123))
			(2 (Say You 415 1 #dispose #caller self))
			(3 (= ticks 123))
			(4
				(Say Inspector_Desmond 415 2 #dispose #caller self)
			)
			(5 (= ticks 123))
			(6 (Say You 415 3 #dispose #caller self))
			(7 (= ticks 123))
			(8
				(Say Inspector_Desmond 415 4 #dispose #caller self)
			)
			(9 (= ticks 30))
			(10
				(Say Inspector_Desmond 415 5 #dispose #caller self)
			)
			(11 (= ticks 30))
			(12
				(Say Inspector_Desmond 415 6 #dispose #caller self)
			)
			(13 (= ticks 30))
			(14
				(Say Inspector_Desmond 415 7 #dispose #caller self)
			)
			(15 (= ticks 30))
			(16
				(Say Inspector_Desmond 415 8 #dispose #caller self)
			)
			(17 (= ticks 30))
			(18
				(Say Inspector_Desmond 415 9 #dispose #caller self)
			)
			(19 (= ticks 30))
			(20
				(TimePrint 415 10 67 -1 185 70 280)
				(Say You 415 11 #dispose #caller self)
			)
			(21 (= ticks 123))
			(22
				(Say You 415 12 #dispose #caller self)
			)
			(23 (= ticks 123))
			(24
				(Say Inspector_Desmond 415 13 #dispose #caller self)
			)
			(25 (= ticks 210))
			(26 (curRoom newRoom: 410))
		)
	)
)
