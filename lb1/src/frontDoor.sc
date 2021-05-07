;;; Sierra Script 1.0 - (do not remove this comment)
(script# 783)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	frontDoor 0
)

(local
	saveBits
	saveBits2
	theX
	theY
	local4
	local5
	local6
)
(procedure (localproc_000c)
	(= saveBits
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at theX theY
			p_width 300
			p_color vWHITE
			p_save
		)
	)
)

(procedure (localproc_002e)
	(Display 783 0 p_restore saveBits)
)

(procedure (localproc_003c)
	(= saveBits2
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at theX theY
			p_width 300
			p_color vBLACK
			p_save
		)
	)
)

(procedure (localproc_005d)
	(Display 783 0 p_restore saveBits2)
)

(instance Laura of Prop)

(instance frontDoor of Room
	(properties
		picture 80
	)
	
	(method (init)
		(super init:)
		(addToPics add: knocker eachElementDo: #init doit:)
		(LoadMany SOUND 6 16 121)
		(Load FONT 41)
		(Door setPri: 14 init: stopUpd:)
		(Jeeves setLoop: 0 init: stopUpd:)
		(Eye setLoop: 3 setPri: 14)
		(Mouth setPri: 14 init: stopUpd: hide:)
		(light1 setPri: 15 init: stopUpd:)
		(Laura
			view: 280
			loop: 0
			cel: 0
			setPri: 14
			ignoreActors: TRUE
			posn: 70 163
			init:
			stopUpd:
		)
		(Lillian setPri: 14 init: stopUpd:)
		(self setScript: openning)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
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
						(event claimed: TRUE)
						(curRoom newRoom: 209)
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
			(curRoom newRoom: 44)
		)
	)
)

(instance openning of Script
	(properties)
	
	(method (doit)
		(if (== (Jeeves x?) 167)
			(Jeeves setCycle: EndLoop)
		)
		(if (== state 4)
			(cond 
				((> local6 -1)
					(if (== (mod local6 2) 1)
						(Door cel: 0)
						(Jeeves cel: 0)
						(light1 cel: 0)
						(Laura cel: 1)
						(Lillian cel: 1)
					else
						(if (== local6 4)
							(Thunder number: 121 loop: 1 play:)
						)
						(Door cel: 1)
						(Jeeves cel: 1)
						(light1 cel: 1)
						(Laura cel: 0)
						(Lillian cel: 0)
					)
					(-- local6)
				)
				((== local6 -1)
					(= local6 -2)
					(= cycles 1)
				)
			)
		)
		(if
			(and
				(== (Thunder number?) 16)
				(== (Thunder prevSignal?) -1)
				(== state 12)
			)
			(client setScript: 0)
			(curRoom newRoom: 209)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 7)
			)
			(1
				(= theX 11)
				(Thunder number: 6 loop: 1 play:)
				(= theY 52)
				(localproc_003c 783 1)
				(= theX 10)
				(= theY 50)
				(localproc_000c 783 1)
				(= seconds 5)
			)
			(2
				(localproc_002e)
				(localproc_005d)
				(Door cycleSpeed: 2 setCycle: EndLoop self)
			)
			(3
				(Door
					loop: 3
					cycleSpeed: 0
					posn: 89 165
					setPri: 15
					hide:
					stopUpd:
				)
				(Jeeves setMotion: MoveTo 131 164 self)
			)
			(4
				(Thunder stop:)
				(Lillian startUpd:)
				(Laura startUpd:)
				(Jeeves loop: 1)
				(Door show: startUpd:)
				(light1 show: startUpd:)
				(= local6 6)
			)
			(5
				(Thunder stop:)
				(Thunder number: 16 loop: -1 play:)
				(Lillian cel: 0 stopUpd:)
				(Door hide:)
				(light1 hide:)
				(= cycles 5)
			)
			(6
				(Eye init: setScript: (Clone eyeball))
				(LEye setPri: 15 init: setScript: (Clone eyeball))
				(EEye setPri: 15 init: setScript: (Clone eyeball))
				(Mouth show: setCycle: EndLoop)
				(Print 783 2
					#at 105 30
					#mode teJustCenter
					#draw
					#dispose
				)
				(= seconds 4)
			)
			(7
				(Mouth setCycle: 0 cel: 0)
				(cls)
				(Print 783 3
					#at 105 140
					#draw
					#dispose
				)
				(= seconds 5)
			)
			(8
				(cls)
				(Print 783 4
					#at 105 30
					#mode teJustCenter
					#draw
					#dispose
				)
				(= local4 2)
				(Mouth setScript: mouthCyc)
				(= seconds 3)
			)
			(9
				(cls)
				(Print 783 5
					#at 63 30
					#mode teJustCenter
					#draw
					#dispose
				)
				(= local4 5)
				(Mouth setScript: mouthCyc)
				(= seconds 5)
			)
			(10
				(cls)
				(Print 783 6
					#at 78 30
					#mode teJustCenter
					#draw
					#dispose
				)
				(= local4 6)
				(Mouth setScript: mouthCyc)
				(= seconds 5)
			)
			(11
				(cls)
				(Print 783 7
					#at 75 30
					#mode teJustCenter
					#draw
					#dispose
				)
				(= local4 6)
				(Mouth setScript: mouthCyc)
				(= seconds 5)
			)
			(12
				(cls)
				(Mouth setCycle: 0 cel: 0)
				(Thunder fade: self)
			)
			(13
				(client setScript: 0)
				(curRoom newRoom: 209)
			)
		)
	)
)

(instance eyeball of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: BegLoop)
				(= seconds (Random 1 6))
			)
			(1
				(= state -1)
				(= seconds (Random 1 6))
			)
		)
	)
)

(instance mouthCyc of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop self)
			)
			(1
				(if (== local4 (++ local5))
					(= local5 0)
					(client cel: 0 setScript: 0)
				else
					(= state -1)
					(= cycles 1)
				)
			)
		)
	)
)

(instance knocker of PicView
	(properties
		y 138
		x 203
		view 180
		loop 1
		priority 15
		signal ignrAct
	)
)

(instance Jeeves of Actor
	(properties
		y 164
		x 200
		view 455
	)
)

(instance Eye of Prop
	(properties
		y 88
		x 127
		view 455
	)
)

(instance Mouth of Prop
	(properties
		y 105
		x 127
		view 455
		loop 2
	)
)

(instance Lillian of Prop
	(properties
		y 163
		x 250
		view 280
		loop 1
	)
)

(instance LEye of Prop
	(properties
		y 122
		x 253
		view 280
		loop 3
	)
)

(instance EEye of Prop
	(properties
		y 126
		x 73
		view 280
		loop 2
	)
)

(instance light1 of Prop
	(properties
		y 165
		x 206
		view 180
		loop 2
		cel 1
	)
)

(instance Door of Prop
	(properties
		y 164
		x 75
		view 180
	)
)

(instance Thunder of Sound)
