;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Sound)
(use Motion)
(use System)

(public
	rm620 0
)

(local
	local0
	[local1 45] = [0 0 0 0 100 55 0 119 40 5 87 16 0 43 45 5 21 7 0 98 45 1 154 67 2 212 7 3 170 10 4 145 11 5 105 5 0 98 28 5 113 12 -1 142 13]
)
(instance rm620 of SQRoom
	(properties
		picture 620
	)
	
	(method (init)
		(Bset fMetBikers)
		(ego
			init:
			view: 625
			setLoop: 3
			normal: 0
			posn: 100 55
			setScript: flyScript
			setPri: 5
			illegalBits: cWHITE
			ignoreActors: TRUE
			setStep: 6 6
		)
		(mono1 init: setCel: 1 setPri: 4)
		(mono2 init: setPri: 4)
		(mono3 init: setPri: 14)
		(monoTalker init: 0 0 mono3)
		(super init:)
		(self setRegions: ULENCE)
		(if (!= prevRoomNum 615)
			(globalSound vol: 85 number: 804 loop: -1 playBed:)
		)
		(Load SOUND 881)
		(Load SOUND 882)
		(music flags: mNOPAUSE)
		(globalSound flags: mNOPAUSE)
		(self setScript: mono3Script)
	)
)

(instance mono3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mono1 setCel: 0)
				(= cycles 2)
			)
			(1
				(monoTalker
					say: 1 self 2 #at 177 5 #color myTopBordColor #back myLowlightColor #width 150
				)
			)
			(2
				(monoTalker
					say: 3 self 2 #at 177 5 #color myTopBordColor #back myLowlightColor #width 140
				)
			)
			(3
				(monoTalker
					say: 4 self 2 #at 177 5 #color myTopBordColor #back myLowlightColor #width 140
				)
			)
			(4
				(monoTalker
					say: 6 self 2 #at 177 5 #color myTopBordColor #back myLowlightColor #width 150
				)
			)
			(5
				(mono3 setCycle: 0)
				(ego setScript: 0 setMotion: 0)
				(globalSound fade: 70 10 10 0)
				(curRoom newRoom: 610)
			)
			(6
				(mono3 setCel: 0)
				(if (not local0)
					(= local0 TRUE)
				else
					(mono1 setCycle: Forward)
					(= local0 FALSE)
				)
			)
		)
	)
)

(instance mono1 of Sq4Prop
	(properties
		x 77
		y 53
		view 625
		loop 4
	)
)

(instance mono2 of Sq4Prop
	(properties
		x 143
		y 27
		view 625
		loop 1
	)
)

(instance mono3 of Sq4Prop
	(properties
		x 229
		y 50
		view 625
	)
)

(instance flyScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(mono2 setCel: 1)
				(ego setCycle: EndLoop self)
			)
			(2 (= cycles (Random 10 15)))
			(3 (ego setCycle: BegLoop self))
			(4 (= cycles (Random 5 10)))
			(5
				(mono2 setCel: 0)
				(ego setCycle: EndLoop self)
			)
			(6 (= cycles (Random 5 10)))
			(7 (ego setCycle: BegLoop self))
			(8
				(buzzSound play:)
				(ego setMotion: MoveTo 119 40 self)
			)
			(9
				(ego setMotion: MoveTo 87 16 self setPri: 3)
			)
			(10
				(ego setMotion: MoveTo 43 45 self)
			)
			(11
				(mono2 setCel: 5)
				(ego setMotion: MoveTo 21 7 self)
			)
			(12
				(ego setMotion: MoveTo 98 45 self setPri: 5)
			)
			(13
				(mono2 setCel: 1)
				(ego setMotion: MoveTo 154 67 self)
			)
			(14
				(ego setMotion: MoveTo 212 7 self)
			)
			(15
				(ego setMotion: MoveTo 170 10 self)
			)
			(16
				(mono2 setCycle: CycleTo 3 1)
				(ego setMotion: MoveTo 145 11 self)
			)
			(17
				(ego setMotion: MoveTo 105 5 self)
			)
			(18
				(mono2 setCel: 0)
				(ego setMotion: MoveTo 98 28 self)
			)
			(19
				(mono2 setCel: 5)
				(ego setMotion: MoveTo 113 12 self)
			)
			(20
				(ego setMotion: MoveTo 142 13 self)
			)
			(21
				(buzzSound number: 882 play:)
				(mono2 setLoop: 2 setCycle: EndLoop self)
			)
			(22 (= cycles (Random 5 10)))
			(23
				(ego hide:)
				(mono2 setCycle: BegLoop self)
			)
			(24
				(mono2 setLoop: 1 setCycle: CycleTo 1 1)
				(= seconds 5)
			)
			(25 (self dispose:))
		)
	)
)

(instance buzzSound of Sound
	(properties
		flags mNOPAUSE
		number 881
	)
)

(instance monoTalker of FaceTalker
	(properties
		noun MONO
		modNum 620
		talkerNum MONO
	)
)
