;;; Sierra Script 1.0 - (do not remove this comment)
(script# 405)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Talker)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm405 0
)

(local
	local0
)
(instance rm405 of LLRoom
	(properties
		picture 405
		west 400
	)
	
	(method (init)
		(LoadMany VIEW 405 406 407 1407)
		(ego init: view: 406)
		(clubOwner init: setCycle: Forward)
		(ego edgeHit: 0 setLoop: 1)
		(ownersDoor init: caller: rm405)
		(theMusic number: 405 flags: 1 setLoop: -1 play:)
		(super init:)
	)
	
	(method (dispose)
		(theMusic fade: 0 15 12 1 self)
		(super dispose:)
	)
	
	(method (cue)
		(if (== (ownersDoor state?) 0)
			(self setScript: sCartoon)
		)
	)
)

(instance ownersDoor of Door
	(properties
		x 94
		y 114
		description {the door}
		approachX 109
		approachY 124
		view 405
		signal $4000
		entranceTo 400
		moveToX 71
		moveToY 123
		enterType 0
		exitType 0
	)
)

(instance clubOwner of Prop
	(properties
		x 218
		y 140
		view 407
	)
)

(instance the_Piano_Pit_Manager of Talker
	(properties
		nsTop 13
		nsLeft 10
		view 1407
		loop 3
		viewInPrint 1
		name "the Piano Pit Manager"
	)
	
	(method (init)
		(= eyes ownerEyes)
		(= bust ownerBust)
		(= mouth ownerMouth)
		(super init: &rest)
	)
)

(instance ownerBust of Prop
	(properties
		view 1407
		loop 1
	)
)

(instance ownerEyes of Prop
	(properties
		nsTop 9
		nsLeft 31
		view 1407
		loop 2
		cycleSpeed 70
	)
)

(instance ownerMouth of Prop
	(properties
		nsTop 15
		nsLeft 36
		view 1407
		cycleSpeed 5
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 165 137 self setPri: 15)
			)
			(1
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(clubOwner setCycle: 0 setCel: 0 stopUpd:)
				(Say ego 405 0
					#at 15 15
					#width 160
				)
				(Say the_Piano_Pit_Manager 405 1 #dispose #caller self)
			)
			(3 (= ticks 30))
			(4
				(Say the_Piano_Pit_Manager 405 2 #dispose #caller self)
			)
			(5 (= ticks 123))
			(6
				(Say ego 405 3 #at 15 15 #width 160)
				(= seconds 3)
			)
			(7
				(Say the_Piano_Pit_Manager 405 4 #dispose #caller self)
			)
			(8 (= ticks 60))
			(9
				(Say the_Piano_Pit_Manager 405 5 #dispose #caller self)
			)
			(10 (= ticks 60))
			(11
				(Say the_Piano_Pit_Manager 405 6 #dispose #caller self)
			)
			(12 (= ticks 60))
			(13
				(TimePrint 405 7 #at -1 185)
				(Say the_Piano_Pit_Manager 405 8 #dispose #caller self)
			)
			(14 (= ticks 30))
			(15
				(Say the_Piano_Pit_Manager 405 9 #dispose #caller self)
			)
			(16 (= ticks 123))
			(17
				(Say ego 405 10 #at 15 15 #width 160)
				(Say the_Piano_Pit_Manager 405 11 #dispose #caller self)
			)
			(18 (= ticks 123))
			(19
				(Say ego 405 12 #at 15 15 #width 160)
				(Say the_Piano_Pit_Manager 405 13 #dispose #caller self)
			)
			(20 (= ticks 60))
			(21
				(Say the_Piano_Pit_Manager 405 14 #dispose #caller self)
			)
			(22 (= ticks 123))
			(23
				(Say ego 405 15 #at 15 15 #width 160)
				(Say the_Piano_Pit_Manager 405 16 #dispose #caller self)
			)
			(24 (= ticks 60))
			(25
				(Say ego 405 17 #at 15 15 #width 160)
				(Say the_Piano_Pit_Manager 405 18 #dispose #caller self)
			)
			(26 (= ticks 123))
			(27
				(ego cel: 4 setCycle: BegLoop self)
				(clubOwner startUpd: setCycle: Forward)
			)
			(28
				(Say ego 405 19 #at 15 15 #width 160)
				(ego
					setCycle: Forward
					setLoop: 2
					setMotion: MoveTo 109 124 self
				)
			)
			(29
				(ego setCycle: 0)
				(ego setPri: -1)
				(= cycles 1)
			)
			(30
				(ownersDoor setCycle: EndLoop self)
				(if (ownersDoor openSnd?)
					((Sound new:) number: (ownersDoor openSnd?) play:)
				)
			)
			(31
				(ego
					setCycle: Forward
					setPri: -1
					setMotion: MoveTo 71 123 self
				)
			)
			(32
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)
