;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm150 0
)

(local
	local0
)
(instance rm150 of LLRoom
	(properties
		picture 145
	)
	
	(method (init)
		(LoadMany VIEW 145 146 1151 1145 1150 1215)
		(LoadMany SOUND 140 150 146 147)
		(HandsOff)
		(fScruemall init:)
		(pArm init:)
		(pLarry init:)
		(super init:)
		(SetFFRoom 155)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theMusic fade: 0 15 12 1)
	)
)

(instance fScruemall of Prop
	(properties
		x 153
		y 139
		view 145
		priority 10
		signal fixPriOn
	)
)

(instance fScruemallTopHalf of Prop
	(properties
		x 179
		y 95
		view 145
		loop 1
		priority 10
		signal fixPriOn
	)
)

(instance pLarry of Prop
	(properties
		x 96
		y 107
		view 146
		priority 9
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance pArm of Prop
	(properties
		x 96
		y 109
		view 146
		loop 1
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance light of Prop
	(properties
		x 200
		y 26
		view 145
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance larryCam of View
	(properties
		x 147
		y 170
		view 150
	)
)

(instance You_a of Talker
	(properties
		nsTop 88
		nsLeft 64
		view 1151
		loop 3
		talkWidth 314
		name "You"
	)
	
	(method (init)
		(= mouth You_b)
		(super init: &rest)
	)
)

(instance You_b of Prop
	(properties
		view 1151
		cycleSpeed 5
	)
)

(instance Silas_Scruemall_a of Talker
	(properties
		x -1
		y 185
		nsTop 21
		nsLeft 154
		view 1150
		loop 3
		talkWidth 240
		name "Silas Scruemall"
	)
	
	(method (init)
		(= mouth Silas_Scruemall_b)
		(= bust bossmallBust)
		(= eyes bossmallEyes)
		(super init: &rest)
	)
)

(instance bossmallBust of Prop
	(properties
		view 1150
		loop 1
	)
)

(instance bossmallEyes of Prop
	(properties
		nsTop 31
		nsLeft 16
		view 1150
		loop 2
		cycleSpeed 70
	)
)

(instance Silas_Scruemall_b of Prop
	(properties
		nsTop 55
		nsLeft 6
		view 1150
		cycleSpeed 5
		name "Silas Scruemall"
	)
)

(instance Silas_Scruemall_c of Talker
	(properties
		x -1
		y 185
		nsTop 27
		nsLeft 167
		view 1145
		loop 3
		talkWidth 280
		name "Silas Scruemall"
	)
	
	(method (init)
		(= bust ScruemallBust)
		(= eyes ScruemallEyes)
		(= mouth ScruemallMouth)
		(super init: &rest)
	)
)

(instance ScruemallBust of Prop
	(properties
		view 1145
		loop 1
	)
)

(instance ScruemallEyes of Prop
	(properties
		nsTop 15
		nsLeft 10
		view 1145
		loop 2
		cycleSpeed 70
	)
)

(instance ScruemallMouth of Prop
	(properties
		nsLeft 5
		view 1145
		cycleSpeed 5
	)
)

(instance breakPot of Sound
	(properties
		flags mNOPAUSE
		number 146
	)
)

(instance yeow of Sound
	(properties
		flags mNOPAUSE
		number 147
	)
)

(instance plink of Sound
	(properties
		flags mNOPAUSE
		number 148
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(fScruemall setCycle: CycleTo 1 1 self)
			)
			(2
				(pLarry setCycle: EndLoop)
				(pArm setCycle: EndLoop)
				(fScruemall setCycle: EndLoop self)
				(breakPot play:)
			)
			(3
				(yeow play:)
				(TimePrint 150 0 #at -1 185)
				(= seconds 3)
			)
			(4
				(fScruemall setCel: 0 setLoop: 3 stopUpd:)
				(fScruemallTopHalf init: stopUpd:)
				(= ticks 10)
			)
			(5
				(Say Silas_Scruemall_c 150 1 #dispose #caller self)
			)
			(6 (= ticks 123))
			(7
				(Say Silas_Scruemall_c 150 2 #dispose #caller self)
			)
			(8 (= ticks 123))
			(9
				(fScruemallTopHalf
					startUpd:
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(plink play:)
				(light init: cycleSpeed: 24 setCycle: EndLoop)
			)
			(10 (= ticks 60))
			(11
				(TimePrint 150 3
					#title {Silas Scruemall}
					#at -1 185
					#dispose
				)
				(= seconds 5)
			)
			(12
				(pLarry dispose:)
				(fScruemall dispose:)
				(fScruemallTopHalf dispose:)
				(pArm dispose:)
				(light dispose:)
				(curRoom style: IRISOUT drawPic: 150 picture: 150 curPic: 150)
				(theMusic number: 150 flags: mNOPAUSE setLoop: -1 play:)
				(Say Silas_Scruemall_a 150 4 #dispose #caller self)
			)
			(13 (= ticks 30))
			(14
				(Say Silas_Scruemall_a 150 5 #dispose #caller self)
			)
			(15 (= ticks 123))
			(16
				(Say You_a 150 6 #dispose #caller self)
			)
			(17 (= ticks 123))
			(18
				(Say Silas_Scruemall_a 150 7 #dispose #caller self)
			)
			(19 (= ticks 30))
			(20
				(Say Silas_Scruemall_a 150 8 #dispose #caller self)
			)
			(21 (= ticks 123))
			(22
				(Say You_a 150 9 #dispose #caller self)
			)
			(23 (= ticks 30))
			(24
				(TimePrint 150 10 #at -1 185)
				(= seconds 3)
			)
			(25
				(Say Silas_Scruemall_a 150 11 #dispose #caller self)
			)
			(26 (= ticks 30))
			(27
				(Say Silas_Scruemall_a 150 12 #dispose #caller self)
			)
			(28 (= ticks 30))
			(29
				(TimePrint 150 13 #at -1 185)
				(= seconds 3)
			)
			(30
				(Say Silas_Scruemall_a 150 14 #dispose #caller self)
			)
			(31 (= ticks 123))
			(32
				(Say You_a 150 15 #dispose #caller self)
			)
			(33 (= ticks 30))
			(34
				(TimePrint 150 16)
				(= seconds 3)
			)
			(35
				(Say Silas_Scruemall_a 150 17 #dispose #caller self)
			)
			(36 (= ticks 30))
			(37
				(Say Silas_Scruemall_a 150 18 #dispose #caller self)
			)
			(38
				(larryCam init:)
				(SolvePuzzle 1 fGetCamcorder)
				(= ticks 123)
			)
			(39
				(Say You_a 150 19 #dispose #caller self)
			)
			(40 (= seconds 3))
			(41
				(Say Silas_Scruemall_a 150 20 #dispose #caller self)
			)
			(42 (= ticks 123))
			(43
				(You_a talkWidth: 160)
				(Say You_a 150 21 #dispose #caller self)
			)
			(44 (= seconds 3))
			(45
				(Say Silas_Scruemall_a 150 22 #dispose #caller self)
			)
			(46 (= ticks 123))
			(47
				(larryCam dispose:)
				(You_a talkWidth: 240)
				(Say You_a 150 23 #dispose #caller self)
			)
			(48 (= seconds 3))
			(49
				(Say Silas_Scruemall_a 150 24 #dispose #caller self)
			)
			(50 (= ticks 30))
			(51
				(TimePrint 150 25 67 -1 20)
				(You_a talkWidth: 240)
				(Say You_a 150 26 #dispose #caller self)
			)
			(52 (= ticks 123))
			(53
				(Say Silas_Scruemall_a 150 27 #dispose #caller self)
			)
			(54 (= ticks 30))
			(55
				(Say Silas_Scruemall_a 150 28 #dispose #caller self)
			)
			(56 (= ticks 123))
			(57
				(TimePrint 150 29 67 -1 20)
				(Say Silas_Scruemall_a 150 30 #dispose #caller self)
				(Bset fAfterCoffee)
			)
			(58 (= ticks 123))
			(59
				(DrawPic 1 -32762)
				(= seconds 3)
			)
			(60
				(TimePrint 150 31)
				(= seconds 3)
			)
			(61
				(TimePrint 150 32 #at -1 185)
				(curRoom newRoom: 155)
			)
		)
	)
)
