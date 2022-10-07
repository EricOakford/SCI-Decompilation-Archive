;;; Sierra Script 1.0 - (do not remove this comment)
(script# 811)
(include sci.sh)
(use Main)
(use KQ6Print)
(use rm810)
(use CartoonScript)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	studyInset 0
)

(instance studyInset of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 811)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not ((ScriptID 80 0) tstFlag: 711 16))
					(theGame givePoints: 1)
				)
				(self setScript: convScript self)
			)
			(1
				(if (not ((ScriptID 80 0) tstFlag: 711 16))
					((ScriptID 80 0) setFlag: 711 16)
					(convScript start: (+ (convScript state?) 1))
					(= register 1)
					(theMusic fadeTo: 150 -1)
					(self setScript: writeLetter self)
				else
					(= register 0)
					(roomConv add: -1 4 1 8 2 init: self)
				)
			)
			(2
				(cast eachElementDo: #startUpd)
				(= cycles 1)
			)
			(3
				(roomConv caller: 0)
				(candles dispose:)
				(feather dispose:)
				(desk dispose:)
				(door dispose:)
				(background dispose:)
				(proc810_1)
				(= cycles 5)
			)
			(4
				(if register
					(messager say: 4 1 9 9 self oneOnly: 0)
					(theMusic fadeTo: 810 -1)
				)
				(ego setCycle: BegLoop self)
			)
			(5
				(theGame handsOn:)
				(ego reset: 1 900)
				(self dispose:)
			)
		)
	)
)

(instance writeLetter of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 59)
				((ScriptID 810 3) setScript: 0 1)
				(self setScript: inkUp self)
			)
			(1
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 2
					modeless: 1
					ticks: 20
					init: self
				)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 3
					modeless: 1
					init: self
				)
			)
			(3 (self setScript: inkUp self))
			(4
				(if modelessDialog (modelessDialog dispose:))
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 4
					modeless: 1
					init: self
				)
			)
			(5
				(inkUp start: 0)
				(self setScript: inkUp self)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 5
					modeless: 1
					init: self
				)
			)
			(7 (self setScript: inkUp self))
			(8
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 6
					modeless: 1
					init: self
				)
			)
			(9
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 4 1 9 7
					modeless: 1
					init: self
				)
			)
			(10
				(self setScript: inkUp self 1)
			)
			(11
				(vizier setLoop: 3 cel: 0 posn: 165 116 priority: 9)
				(= cycles 10)
			)
			(12
				(if ((ScriptID 80 0) tstFlag: 710 256)
					(KQ6Print
						font: userFont
						posn: -1 10
						modeless: 1
						say: 0 4 1 9 8
						init: self
					)
				else
					(KQ6Print
						font: userFont
						posn: -1 10
						modeless: 1
						say: 0 4 1 10 1
						init: self
					)
				)
			)
			(13
				(if modelessDialog (modelessDialog dispose:))
				(self setScript: convScript self)
			)
			(14 (Bclr 59) (self dispose:))
		)
	)
)

(instance inkUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(vizier
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(1 (= seconds (Random 3 5)))
			(2 (vizier setCycle: BegLoop self))
			(3
				(= start state)
				(vizier setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(4 (= seconds 2))
			(5
				(if register
					(vizier setLoop: 8 cel: 0 setCycle: CycleTo 1 1 self)
				else
					(vizier setCycle: BegLoop self)
				)
			)
			(6
				(if register
					(feather init:)
					(vizier setCycle: EndLoop self)
				else
					(vizier setLoop: 0 cycleSpeed: 20 setCycle: Reverse)
					(self dispose:)
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance convScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 814
					normal: 0
					loop: 0
					cel: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					x: 164
					y: 169
					setCycle: EndLoop self
				)
			)
			(1
				(KQ6Print
					font: userFont
					say: 0 4 1 (+ 8 (not ((ScriptID 80 0) tstFlag: 711 16))) 1
					init: self
				)
				(= cycles 10)
			)
			(2
				(cast eachElementDo: #startUpd)
				(= ticks 1)
			)
			(3
				(cast eachElementDo: #hide)
				(curRoom drawPic: 802 10)
				(candles setCycle: Forward init:)
				(door init:)
				(desk addToPic:)
				(background addToPic:)
				(if (not ((ScriptID 80 0) tstFlag: 711 16))
					(feet init:)
					(vizier init:)
					(= cycles 1)
				else
					(feather init:)
					(= seconds 5)
				)
			)
			(4 (self dispose:))
			(5 (vizier setCycle: EndLoop self))
			(6
				(feet dispose:)
				(vizier
					posn: 153 113
					setLoop: 4
					cel: 0
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setScale: Scaler 100 73 113 103
					setMotion: MoveTo 92 106 self
				)
			)
			(7
				(door hide:)
				(soundFx2 number: 901 loop: 1 play:)
				(vizier
					setLoop: 5
					cel: 0
					cycleSpeed: 8
					posn: 82 72
					setScale: 0
					scaleX: 128
					scaleY: 128
					setCycle: EndLoop self
				)
			)
			(8
				(soundFx2 number: 902 loop: 1 play:)
				(door show:)
				(vizier dispose:)
				(= cycles 2)
			)
			(9 (self dispose:))
		)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance background of View
	(properties
		x 154
		y 123
		view 811
		loop 1
		signal $4010
	)
)

(instance feet of View
	(properties
		x 165
		y 115
		view 812
		loop 1
		priority 9
		signal $4010
		scaleSignal $0001
	)
)

(instance vizier of Actor
	(properties
		x 164
		y 117
		view 812
		cel 2
		priority 13
		signal $6010
		scaleSignal $0001
		cycleSpeed 19
		illegalBits $0000
	)
)

(instance desk of View
	(properties
		x 137
		y 89
		view 811
		loop 1
		cel 1
		priority 14
		signal $0010
	)
)

(instance candles of Prop
	(properties
		x 185
		y 91
		view 811
		loop 2
		cel 1
		priority 15
		signal $0010
	)
)

(instance feather of View
	(properties
		x 170
		y 95
		view 811
		loop 1
		cel 2
		priority 15
		signal $4010
	)
)

(instance door of View
	(properties
		x 92
		y 74
		view 812
		loop 6
		priority 1
		signal $4010
	)
)
