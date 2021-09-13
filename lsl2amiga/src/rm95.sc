;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm95 0
)

(local
	local0
)
(instance rm95 of Room
	(properties
		picture 95
		horizon 1
	)
	
	(method (init)
		(Load VIEW 191)
		(Load VIEW 829)
		(Load VIEW 110)
		(Load VIEW 113)
		(Load SOUND 105)
		(super init:)
		(theSound play:)
		(addToPics add: aView1 aView2 aView3 aView4 doit:)
		(aBed
			setLoop: 9
			setPri: 5
			stopUpd:
			init:
		)
		(aBigEgo
			init:
		)
		(aBigEgoFace
			cycleSpeed: 5
			setPri: 15
			init:
		)
		(aAcid
			setLoop: 4
			setPri: 1
			cycleSpeed: 2
			init:
			hide:
		)
		(aDoor
			setLoop: 6
			illegalBits: 0
			setStep: 1 1
			setPri: 14
			stopUpd:
			init:
		)
		(aChainWest
			setLoop: 0
			posn: 218 -5
			ignoreHorizon:
			illegalBits: 0
			setPri: 4
			setStep: 1 3
			init:
		)
		(aChainEast
			setLoop: 0
			posn: 250 -5
			ignoreHorizon:
			illegalBits: 0
			setPri: 4
			setStep: 1 3
			init:
		)
		(aLaser
			setLoop: 1
			ignoreHorizon:
			illegalBits: 0
			setPri: 13
			moveSpeed: 1
			setStep: 1 1
			init:
		)
		(aBeam
			setLoop: 3
			ignoreHorizon:
			illegalBits: 0
			setPri: 12
			setCycle: Walk
			moveSpeed: 1
			setStep: 1 1
			init:
			hide:
		)
		(if (== henchView 0)
			(= henchView 205)
		)
		(aHench
			view: henchView
			illegalBits: 0
			setCycle: Walk
			init:
			setScript: henchScript
		)
		(self setScript: rm95Script)
		(= currentEgoView vEgo)
		(NormalEgo)
		(ego loop: loopS posn: 32 150 init:)
		(HandsOff)
	)
)

(instance rm95Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Print 95 0)
				(aDoor setMotion: MoveTo 9 138 self)
			)
			(2
				(aDoor stopUpd:)
				(henchScript cue:)
			)
			(3
				(ego setMotion: MoveTo 31 183 self)
			)
			(4
				(ego setMotion: MoveTo 163 183 self)
			)
			(5
				(ego setMotion: MoveTo 163 111 self)
			)
			(6
				(ego setMotion: MoveTo 209 111 self)
			)
			(7
				(ego
					view: 191
					setLoop: 1
					setPri: 11
					posn: 235 95
					cycleSpeed: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(ego
					cycleSpeed: 2
					setLoop: 2
					cel: 0
					posn: 235 105
					setCycle: EndLoop self
				)
			)
			(9
				(Print 95 1 #draw)
				(= seconds 3)
			)
			(10
				(ego cycleSpeed: 1 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(11
				(ego setLoop: 7 setCycle: Forward)
				(= cycles 20)
			)
			(12
				(Print 95 2 #at -1 20 #draw)
				(= seconds 3)
			)
			(13
				(Print 95 3 #at -1 20)
				(= seconds 3)
			)
			(14
				(aChainWest setMotion: MoveTo 218 90 self)
				(aChainEast setMotion: MoveTo 250 90)
			)
			(15
				(= seconds 3)
			)
			(16
				(aBed hide:)
				(ego
					setLoop: 4
					cel: 0
					posn: 214 105
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(aChainWest setStep: 1 2 setMotion: MoveTo 218 45)
				(aChainEast setStep: 1 2 setMotion: MoveTo 250 45)
			)
			(17
				(ego setStep: 1 2 setMotion: MoveTo 214 89 self)
			)
			(18
				(ego stopUpd:)
				(aChainWest setMotion: 0 stopUpd:)
				(aChainWest setMotion: 0 stopUpd:)
				(aAcid show: setCycle: EndLoop self)
			)
			(19
				(aAcid setLoop: 2 cycleSpeed: 0 setCycle: Forward)
				(= cycles 15)
			)
			(20
				(Print 95 4 #at -1 20)
				(Print 95 5 #at -1 20)
				(= seconds 3)
			)
			(21
				(aLaser setMotion: MoveTo 234 29 self)
			)
			(22
				(aBigEgo posn: 172 38 stopUpd:)
				(aBigEgoFace posn: 172 38 setCycle: EndLoop)
				(aLaser
					setStep: 1 1
					moveSpeed: 1
					setMotion: MoveTo 234 -2
				)
				(aBeam
					posn: 234 27
					setStep: 1 1
					cycleSpeed: 0
					moveSpeed: 1
					show:
					setMotion: MoveTo 234 21 self
				)
			)
			(23
				(Print 95 6 #at -1 130)
				(aBeam setMotion: MoveTo 234 0 self)
				(aBed view: 191 loop: 0 posn: 214 88 show:)
				(ego
					setLoop: 5
					posn: (+ (ego x?) 8) (- (ego y?) 5)
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop
				)
				(= cycles 10)
			)
			(24
				(aBigEgoFace dispose:)
				(aBigEgo dispose:)
			)
			(25
				(aLaser dispose:)
				(aBeam dispose:)
				(ego
					setLoop: 6
					setCel: 0
					setStep: 1 7
					posn: 235 90
					setMotion: MoveTo 235 134 self
				)
			)
			(26
				(ego setMotion: 0 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(27
				(= seconds 3)
			)
			(28
				(= currentStatus egoDYING)
			)
		)
	)
)

(instance henchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aHench setMotion: MoveTo 37 169 self)
			)
			(2
				(aHench setMotion: MoveTo 39 181 self)
				(rm95Script cue:)
			)
			(3
				(aHench setMotion: MoveTo 139 181 self)
			)
			(4
				(Print 95 7)
				(aHench setMotion: MoveTo 139 131 self)
			)
			(5
				(Print 95 8)
				(aHench setMotion: MoveTo 49 91 self)
			)
			(6
				(aHench setLoop: 3 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		number 105
	)
)

(instance aView1 of PicView
	(properties
		y 175
		x 35
		view 829
		loop 8
		priority 9
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 137
		x 9
		view 829
		loop 5
		priority 13
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 68
		x 33
		view 829
		loop 7
		priority 1
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 68
		x 58
		view 829
		loop 7
		signal ignrAct
	)
)

(instance aBed of View
	(properties
		y 105
		x 214
		view 829
		signal ignrAct
	)
)

(instance aBigEgo of View
	(properties
		y 1038
		x 172
		view 110
		priority 14
		signal ignrAct
	)
)

(instance aBigEgoFace of Prop
	(properties
		y 1038
		x 172
		view 113
		signal ignrAct
	)
)

(instance aAcid of Prop
	(properties
		y 136
		x 235
		view 829
		signal ignrAct
	)
)

(instance aDoor of Actor
	(properties
		y 137
		x 35
		view 829
		signal ignrAct
	)
)

(instance aChainWest of Actor
	(properties
		view 829
		signal ignrAct
	)
)

(instance aChainEast of Actor
	(properties
		view 829
		signal ignrAct
	)
)

(instance aLaser of Actor
	(properties
		y -5
		x 234
		view 829
		signal ignrAct
	)
)

(instance aBeam of Actor
	(properties
		y 27
		x 234
		view 829
		signal ignrAct
	)
)

(instance aHench of Actor
	(properties
		y 154
		x 36
		signal ignrAct
	)
)
