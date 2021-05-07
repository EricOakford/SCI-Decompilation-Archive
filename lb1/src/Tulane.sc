;;; Sierra Script 1.0 - (do not remove this comment)
(script# 779)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Path)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Tulane 0
)

(local
	local0 =  1
	saveBits
	saveBits2
	local3
	tinyLRPoints = [
		98 101
		324 101
		PATHEND
		]
	largeLRPoints = [
		93 135
		138 132
		175 125
		220 126
		247 124
		277 132
		288 133
		304 150
		320 161
		332 161
		PATHEND
		]
	largeRLPoints = [
		320 161
		304 150
		288 133
		277 132
		247 124
		220 126
		175 125
		138 132
		93 135
		14 130
		PATHEND
		]
	largeTBPoints = [
		317 131
		298 137
		304 150
		320 161
		332 161
		PATHEND
		]
)
(procedure (DoPrint theX theY theWidth)
	(Print &rest
		#at theX theY
		#font 4
		#width theWidth
		#mode teJustCenter
		#draw
		#dispose
	)
)

(instance tinyLRPath of Path
	
	(method (at n)
		(return [tinyLRPoints n])
	)
)

(instance largeLRPath of Path
	
	(method (at n)
		(return [largeLRPoints n])
	)
)

(instance largeRLPath of Path
	
	(method (at n)
		(return [largeRLPoints n])
	)
)

(instance largeTBPath of Path
	
	(method (at n)
		(return [largeTBPoints n])
	)
)

(instance statue of PicView
	(properties
		y 112
		x 226
		view 178
		priority 11
		signal ignrAct
	)
)

(instance bench of PicView
	(properties
		y 183
		x 104
		view 178
		cel 1
		priority 13
		signal ignrAct
	)
)

(instance LilysHead of PicView
	(properties
		y 140
		x 127
		view 513
		loop 1
		priority 12
		signal ignrAct
	)
)

(instance LilysLegs of PicView
	(properties
		y 183
		x 128
		view 513
		loop 1
		cel 1
		signal ignrAct
	)
)

(instance LaurasHead of Prop)

(instance LaurasArms of Prop)

(instance squirrel of Prop)

(instance pigeon of Actor)

(instance tiny of Actor)

(instance small of Actor)

(instance large of Actor)

(instance Lillian of Actor)

(instance bFly of Actor)

(instance picWindow of Cage)

(instance campusRag of Sound)

(instance Tulane of Room
	(properties
		picture 78
		style WIPEUP
	)
	
	(method (init)
		(super init:)
		(= talkTimer (= local3 0))
		(HandsOff)
		(Load VIEW 278)
		(Load VIEW 513)
		(Load VIEW 524)
		(Load VIEW 528)
		(Load SOUND 3)
		(Load FONT 4)
		(Load FONT 40)
		(Load FONT 41)
		(addToPics add: bench statue doit:)
		(LaurasHead
			view: 178
			loop: 1
			cel: 0
			posn: 100 142
			setPri: 12
			ignoreActors: TRUE
			stopUpd:
			setScript: LauraReading
			init:
		)
		(LaurasArms
			view: 178
			loop: 3
			cel: 0
			posn: 101 165
			setPri: 14
			ignoreActors: TRUE
			stopUpd:
			init:
		)
		(large view: 530 posn: 10 130 setCycle: Walk init:)
		(small
			view: 529
			setLoop: 3
			posn: 320 110
			setStep: 1 1
			setCycle: Walk
			init:
			hide:
		)
		(tiny
			view: 529
			setLoop: 0
			posn: 12 107
			setPri: 5
			setStep: 1 1
			moveSpeed: 1
			cycleSpeed: 1
			setCycle: Walk
			init:
			hide:
		)
		(Lillian ignoreActors: TRUE init: stopUpd: hide:)
		(picWindow left: -2 right: 321 bottom: 191 top: -2 init:)
		(bFly
			view: 378
			posn: 61 162
			setStep: 3 3
			setPri: 14
			observeBlocks: picWindow
			ignoreHorizon: TRUE
			setMotion: Wander
			cycleSpeed: 0
			setCycle: Walk
			init:
		)
		(squirrel
			view: 206
			loop: 0
			cel: 0
			posn: 18 159
			setPri: 15
			stopUpd:
			init:
		)
		(pigeon
			view: 278
			setLoop: 0
			posn: 26 12
			setPri: 14
			setCycle: Walk
			stopUpd:
			init:
		)
		(self setScript: campusLife)
	)
	
	(method (dispose)
		(DisposeScript WANDER)
		(DisposeScript PATH)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					(
						(or
							(== (event message?) `S)
							(== (event message?) `s)
						)
						(LaurasHead setScript: 0)
						(LaurasArms setScript: 0)
						(Lillian setScript: 0)
						(curRoom newRoom: 780)
					)
					(
						(or
							(== (event message?) ENTER)
							(== (event message?) SPACEBAR)
						)
						(Bset fSkippedIntro)
					)
				)
			)
			(mouseDown
				(Bset fSkippedIntro)
			)
		)
		(if (Btst fSkippedIntro)
			(event claimed: TRUE)
			(LaurasHead setScript: 0)
			(LaurasArms setScript: 0)
			(Lillian setScript: 0)
			(curRoom newRoom: 44)
		)
	)
	
	(method (newRoom n)
		(= talkTimer 0)
		(super newRoom: n)
	)
)

(instance campusLife of Script
	
	(method (doit)
		(super doit:)
		(if (== (campusRag prevSignal?) -1)
			(client setScript: 0)
			(curRoom newRoom: 780)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(campusRag number: 3 loop: -1 play:)
				(= cycles 2)
			)
			(1
				(Print 779 0
					#at 80 20
					#font 40
					#width 160
					#mode teJustCenter
					#draw
					#dispose
				)
				(= seconds 6)
			)
			(2
				(cls)
				(pigeon setScript: Pigeon)
				(large setMotion: largeLRPath self)
				(small show: setMotion: MoveTo -4 110)
			)
			(3
				(tiny show: setMotion: tinyLRPath)
				(= saveBits
					(Display 779 1
						p_at 11 12
						p_width 240
						p_color vBLACK
						p_back -1
						p_font 41
						p_save
					)
				)
				(= saveBits2
					(Display 779 1
						p_at 10 10
						p_width 240
						p_color vYELLOW
						p_back -1
						p_font 41
						p_save
					)
				)
				(= seconds 5)
			)
			(4
				(large view: 524 y: 131 setMotion: largeTBPath)
				(= seconds 5)
			)
			(5
				(squirrel startUpd:)
				(Display 779 2
					p_restore saveBits2
				)
				(Display 779 2
					p_restore saveBits
				)
				(Lillian
					view: 513
					setLoop: 0
					cel: 7
					posn: 320 188
					show:
					setCycle: Walk
					startUpd:
					setMotion: MoveTo 127 183 self
				)
			)
			(6
				(addToPics add: LilysHead LilysLegs doit:)
				(Lillian
					loop: 2
					cel: 0
					x: (- (Lillian x?) 3)
					y: (- (Lillian y?) 26)
					stopUpd:
				)
				(LaurasHead setScript: 0 setCycle: EndLoop)
				(LaurasArms loop: 4 cel: 0 setCycle: EndLoop)
				(squirrel cycleSpeed: 1 setCycle: EndLoop self)
			)
			(7
				(squirrel
					loop: 2
					cel: 0
					posn: 41 159
					cycleSpeed: 8
					setCycle: Forward
				)
				(LaurasArms loop: 5 cel: 0 setCycle: EndLoop)
				(Lillian setScript: LillianSpeaking)
				(DoPrint 170 25 130 779 3)
				(= seconds 8)
			)
			(8
				(squirrel loop: 3)
				(large view: 528 setMotion: largeRLPath)
				(= local3 1)
				(LaurasHead loop: 2 cel: 0 stopUpd:)
				(LaurasArms loop: 6 stopUpd: setScript: LauraSpeaking)
				(DoPrint 10 75 100 779 4)
				(= seconds 8)
			)
			(9
				(squirrel
					loop: 1
					cel: 0
					setPri: -1
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(small view: 529 setLoop: 6 setMotion: MoveTo 320 110)
				(LaurasHead cel: 0 forceUpd:)
				(= local3 0)
				(DoPrint 170 40 130 779 5)
				(= seconds 8)
			)
			(10
				(squirrel stopUpd:)
				(= local3 1)
				(DoPrint 10 60 100 779 6)
				(= seconds 5)
			)
			(11
				(LaurasHead cel: 0 forceUpd:)
				(LaurasArms setScript: 0)
				(Lillian setScript: 0)
				(campusRag fade:)
			)
		)
	)
)

(instance LauraReading of Script
	
	(method (changeState newState)
		(if (not (LaurasArms script?))
			(switch (= state newState)
				(0
					(= cycles (Random 20 50))
				)
				(1
					(LaurasArms setCycle: EndLoop self)
				)
				(2
					(LaurasArms stopUpd:)
					(= cycles 1)
					(= state -1)
				)
			)
		)
	)
)

(instance LauraSpeaking of Script
	(properties)
	
	(method (changeState newState)
		(if local3
			(if (== talkTimer 0)
				(= talkTimer (Random 10 20))
				(switch (= state newState)
					(0
						(LaurasHead setCycle: Forward)
						(LaurasArms setCycle: EndLoop self)
					)
					(1
						(LaurasArms setCycle: BegLoop)
					)
				)
			)
		else
			(switch (= state newState)
				(0
					(if (LaurasArms cel?)
						(LaurasArms setCycle: BegLoop self)
						(LaurasHead cel: 0 setCycle: 0 stopUpd:)
					else
						(= cycles 1)
					)
				)
				(1
					(LaurasArms stopUpd:)
				)
			)
		)
		(= state -1)
		(= cycles 1)
	)
)

(instance LillianSpeaking of Script
	
	(method (changeState newState)
		(if (not local3)
			(if (== talkTimer 0)
				(= talkTimer (Random 10 20))
				(switch (= state newState)
					(0 (Lillian setCycle: EndLoop self))
					(1 (Lillian setCycle: BegLoop))
				)
			)
		else
			(switch (= state newState)
				(0
					(if (Lillian cel?)
						(Lillian setCycle: BegLoop self)
					else
						(= cycles 1)
					)
				)
				(1
					(Lillian stopUpd:)
				)
			)
		)
		(= state -1)
		(= cycles 1)
	)
)

(instance Pigeon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(pigeon startUpd: setMotion: MoveTo 205 40 self)
			)
			(1
				(pigeon setCel: 0 setMotion: MoveTo 220 36 self)
			)
			(2
				(pigeon setCel: -1 setMotion: MoveTo 225 38 self)
			)
			(3
				(pigeon setCycle: BegLoop self)
			)
			(4
				(pigeon loop: 1 posn: 219 46 setCycle: EndLoop self)
			)
			(5
				(pigeon addToPic:)
				(client setScript: 0)
			)
		)
	)
)
