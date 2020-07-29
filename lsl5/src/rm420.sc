;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Actor)
(use System)

(public
	rm420 0
)

(instance rm420 of LLRoom
	(properties
		picture 420
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 1420 1421)
		(theMusic number: 425 loop: -1 flags: mNOPAUSE play:)
		(self setScript: sRoom)
	)
)

(instance sRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 123))
			(1
				(Say Inspector_Desmond 420 0 #dispose #caller self)
			)
			(2 (= ticks 60))
			(3 (Say You 420 1 #dispose #caller self))
			(4 (= ticks 60))
			(5
				(Say Inspector_Desmond 420 2 #dispose #caller self)
			)
			(6 (= ticks 60))
			(7
				(Say Inspector_Desmond 420 3 #dispose #caller self)
			)
			(8 (= ticks 60))
			(9
				(Say ego 420 4)
				(= ticks 60)
			)
			(10
				(Say You 420 5 #dispose #caller self)
			)
			(11 (= ticks 60))
			(12
				(Say Inspector_Desmond 420 6 #dispose #caller self)
			)
			(13 (= ticks 60))
			(14
				(Say Inspector_Desmond 420 7 #dispose #caller self)
			)
			(15 (= ticks 60))
			(16
				(Say Inspector_Desmond 420 8 #dispose #caller self)
			)
			(17 (= ticks 60))
			(18
				(Say Inspector_Desmond 420 9 #dispose #caller self)
			)
			(19 (= ticks 60))
			(20
				(Say ego 420 10)
				(= ticks 60)
			)
			(21
				(Say You 420 11 #dispose #caller self)
			)
			(22 (= ticks 60))
			(23
				(Say Inspector_Desmond 420 12 #dispose #caller self)
			)
			(24 (= ticks 60))
			(25
				(Say Inspector_Desmond 420 13 #dispose #caller self)
			)
			(26 (= ticks 60))
			(27
				(Say Inspector_Desmond 420 14 #dispose #caller self)
			)
			(28 (= ticks 60))
			(29
				(Say You 420 15 #dispose #caller self)
			)
			(30 (= ticks 60))
			(31
				(Say Inspector_Desmond 420 16 #dispose #caller self)
			)
			(32 (= ticks 60))
			(33
				(Say Inspector_Desmond 420 17 #dispose #caller self)
			)
			(34 (= ticks 60))
			(35
				(Say You 420 18 #dispose #caller self)
			)
			(36 (= ticks 60))
			(37
				(Say Inspector_Desmond 420 19 #dispose #caller self)
			)
			(38 (= ticks 60))
			(39
				(Say You 420 20 #dispose #caller self)
			)
			(40 (= ticks 60))
			(41
				(Say Inspector_Desmond 420 21 #dispose #caller self)
			)
			(42
				(if modelessDialog (modelessDialog dispose:))
				(theMusic fade:)
				(curRoom newRoom: 430)
			)
		)
	)
)

(instance Inspector_Desmond of Talker
	(properties
		y 160
		nsTop 29
		nsLeft 115
		view 1420
		loop 3
		talkWidth 200
		name "Inspector Desmond"
	)
	
	(method (init)
		(= bust desmondBust)
		(= mouth desmondMouth)
		(super init: &rest)
	)
)

(instance desmondBust of Prop
	(properties
		view 1420
		loop 1
	)
)

(instance desmondMouth of Prop
	(properties
		nsTop 13
		view 1420
		cycleSpeed 8
	)
)

(instance You of Talker
	(properties
		x 140
		y 170
		nsTop 59
		nsLeft 199
		view 1421
		loop 3
	)
	
	(method (init)
		(= mouth pattiMouth)
		(super init: &rest)
	)
)

(instance pattiMouth of Prop
	(properties
		view 1421
		cycleSpeed 8
	)
)
