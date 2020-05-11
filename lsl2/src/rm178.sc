;;; Sierra Script 1.0 - (do not remove this comment)
(script# 178)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm178 0
)

(local
	local0
	shaman
	chair
	mirror
	egoFace
	mirrorHandle
)
(instance rm178 of Room
	(properties
		picture 125
	)
	
	(method (init)
		(Load VIEW 196)
		(Load VIEW 711)
		(Load VIEW 712)
		(Load VIEW 713)
		(Load VIEW 232)
		(Load PICTURE 178)
		(super init:)
		((= mirror (Prop new:))
			view: 717
			setLoop: 3
			setCel: 0
			setPri: 14
			ignoreActors:
			posn: 160 1128
			setPri: 14
			init:
		)
		((= mirrorHandle (View new:))
			view: 717
			setLoop: 3
			setCel: 1
			ignoreActors:
			posn: 160 1129
			setPri: 14
			init:
		)
		((= egoFace (Prop new:))
			view: 717
			setLoop: 3
			setCel: 2
			ignoreActors:
			posn: 161 1120
			setPri: 15
			init:
		)
		((= chair (Prop new:))
			view: 232
			loop: 1
			cel: 0
			posn: 164 118
			setPri: 8
			ignoreActors:
			stopUpd:
			init:
		)
		((= shaman (Actor new:))
			view: 711
			loop: 3
			setCycle: Walk
			illegalBits: 0
			posn: 151 161
			ignoreActors:
			init:
		)
		(ego posn: 171 161 illegalBits: 0 ignoreActors: init:)
		(self setRegions: 7 setScript: rm178Script)
		(HandsOff)
	)
)

(instance rm178Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 181 132)
				(shaman setMotion: MoveTo 159 122 self)
			)
			(1
				(ego loop: 3)
				(Print 178 0 #at -1 152 #draw)
				(= seconds 3)
			)
			(2
				(Print 178 1)
				(shaman view: 713 setCycle: Forward)
				(= cycles 50)
			)
			(3
				(chair view: 712 setLoop: 0 setCycle: Forward)
				(shaman view: 712 posn: 157 1120)
				(= cycles 50)
			)
			(4
				(chair setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(shaman
					view: 712
					setLoop: 2
					posn: 157 121
					cel: 0
					setCycle: EndLoop self
					show:
				)
			)
			(6
				((View new:)
					view: 712
					ignoreActors:
					posn: 162 111
					loop: 2
					cel: 2
					setPri: 8
					addToPic:
				)
				(shaman
					setLoop: 3
					setStep: 3 3
					posn: 144 135
					setMotion: MoveTo 130 159 self
					forceUpd:
				)
			)
			(7
				(Print 178 2 #draw)
				(= cycles 10)
			)
			(8
				(ego setMotion: MoveTo 157 122 self)
			)
			(9
				(ego setLoop: 2)
				(= cycles 10)
			)
			(10
				(chair view: 232 loop: 1 cel: 0 posn: 164 118 show:)
				(ego
					view: 196
					posn: 157 120
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(11
				(chair stopUpd:)
				(ego setLoop: 1 setCycle: Forward)
				(= cycles 20)
			)
			(12
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(13
				(shaman view: 711 setLoop: 2 posn: 159 121)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(14
				(shaman stopUpd:)
				(ego setLoop: 3 cel: 0 setCycle: Forward)
				(= cycles 30)
			)
			(15
				(ego setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(16
				(ego setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(17
				(ego setLoop: 6 cel: 0 setCycle: Forward)
				(= cycles 32)
			)
			(18
				(ego setLoop: 7 cycleSpeed: 1 cel: 0 setCycle: EndLoop self)
			)
			(19
				(ego setLoop: 8 setCycle: Forward)
				(= cycles 30)
			)
			(20
				(ego setLoop: 7 setCel: 255 setCycle: BegLoop self)
			)
			(21
				(ego setLoop: 6 cycleSpeed: 0 cel: 0 setCycle: Forward)
				(= cycles 30)
			)
			(22
				(ego setLoop: 5 setCel: 255 setCycle: BegLoop self)
			)
			(23
				(ego setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(24
				(ego setLoop: 3 cel: 0 setCycle: Forward)
				(= cycles 30)
			)
			(25
				(ego setLoop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(26
				(shaman hide:)
				(ego setLoop: 9)
				(Print 178 3 #at -1 20 #draw)
				(= seconds 3)
			)
			(27
				(Print 178 4)
				(ego stopUpd:)
				(mirror posn: 160 127 stopUpd:)
				(mirrorHandle posn: 160 128 stopUpd:)
				(egoFace posn: 161 119)
				(= seconds 2)
			)
			(28
				(Print 178 5 #at -1 20)
				(= seconds 5)
			)
			(29
				(mirror posn: 160 1128)
				(mirrorHandle posn: 160 1229)
				(egoFace posn: 161 1320)
				(= seconds 3)
			)
			(30
				(Print 178 6)
				(Print 178 7)
				(= seconds 3)
			)
			(31
				(mirror
					setLoop: 0
					cel: 0
					posn: 154 91
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(32
				(mirror stopUpd:)
				(egoFace
					posn: 115 47
					cycleSpeed: 3
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(33
				(mirror posn: 154 1092)
				(egoFace
					posn: 154 91
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(34
				(curRoom drawPic: 178 16)
				(chair posn: 999 999)
				(ego posn: 999 999)
				(egoFace forceUpd:)
				(curRoom newRoom: 86)
			)
		)
	)
)
