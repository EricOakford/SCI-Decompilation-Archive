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
	aBarber
	aChair
	aThoughtBalloon
	aThought
	aHandle
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
		((= aThoughtBalloon (Prop new:))
			view: 717
			setLoop: 3
			setCel: 0
			setPri: 14
			ignoreActors:
			posn: 160 1128
			setPri: 14
			init:
		)
		((= aHandle (View new:))
			view: 717
			setLoop: 3
			setCel: 1
			ignoreActors:
			posn: 160 1129
			setPri: 14
			init:
		)
		((= aThought (Prop new:))
			view: 717
			setLoop: 3
			setCel: 2
			ignoreActors:
			posn: 161 1120
			setPri: 15
			init:
		)
		((= aChair (Prop new:))
			view: 232
			loop: 1
			cel: 0
			posn: 164 118
			setPri: 8
			ignoreActors:
			stopUpd:
			init:
		)
		((= aBarber (Actor new:))
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
				(aBarber setMotion: MoveTo 159 122 self)
			)
			(1
				(ego loop: 3)
				(Print 178 0 #at -1 152 #draw)
				(= seconds 3)
			)
			(2
				(Print 178 1)
				(aBarber view: 713 setCycle: Forward)
				(= cycles 50)
			)
			(3
				(aChair view: 712 setLoop: 0 setCycle: Forward)
				(aBarber view: 712 posn: 157 1120)
				(= cycles 50)
			)
			(4
				(aChair setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(aBarber
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
				(aBarber
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
				(aChair view: 232 loop: 1 cel: 0 posn: 164 118 show:)
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
				(aChair stopUpd:)
				(ego setLoop: 1 setCycle: Forward)
				(= cycles 20)
			)
			(12
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(13
				(aBarber view: 711 setLoop: 2 posn: 159 121)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(14
				(aBarber stopUpd:)
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
				(aBarber hide:)
				(ego setLoop: 9)
				(Print 178 3 #at -1 20 #draw)
				(= seconds 3)
			)
			(27
				(Print 178 4)
				(ego stopUpd:)
				(aThoughtBalloon posn: 160 127 stopUpd:)
				(aHandle posn: 160 128 stopUpd:)
				(aThought posn: 161 119)
				(= seconds 2)
			)
			(28
				(Print 178 5 #at -1 20)
				(= seconds 5)
			)
			(29
				(aThoughtBalloon posn: 160 1128)
				(aHandle posn: 160 1229)
				(aThought posn: 161 1320)
				(= seconds 3)
			)
			(30
				(Print 178 6)
				(Print 178 7)
				(= seconds 3)
			)
			(31
				(aThoughtBalloon
					setLoop: 0
					cel: 0
					posn: 154 91
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(32
				(aThoughtBalloon stopUpd:)
				(aThought
					posn: 115 47
					cycleSpeed: 3
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(33
				(aThoughtBalloon posn: 154 1092)
				(aThought
					posn: 154 91
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(34
				(curRoom drawPic: 178 16)
				(aChair posn: 999 999)
				(ego posn: 999 999)
				(aThought forceUpd:)
				(curRoom newRoom: 86)
			)
		)
	)
)
