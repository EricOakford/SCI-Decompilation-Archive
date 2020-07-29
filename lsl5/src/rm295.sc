;;; Sierra Script 1.0 - (do not remove this comment)
(script# 295)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Polygon)
(use ForCount)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm295 0
)

(local
	flasherState
	kidState
	cameraManState
	local3
	reporterState
)
(procedure (CameraFlash)
	(switch (Random 0 1)
		(0
			(flash posn: (Random 73 112) (Random 110 151))
		)
		(1
			(flash posn: (Random 192 248) (Random 95 137))
		)
	)
	(flash setCycle: EndLoop)
)

(instance rm295 of LLRoom
	(properties
		picture 290
	)
	
	(method (init)
		(ego
			init:
			normalize: 291
			setCycle: 0
			setLoop: 5
			setCel: 0
			posn: 152 94
			cycleSpeed: 10
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						94 144
						110 145
						90 163
						75 161
						69 167
						74 174
						109 189
						0 189
						0 0
						319 0
						319 189
						202 189
						232 169
						249 165
						249 157
						211 141
						98 140
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						210 152
						221 160
						218 166
						193 167
						176 161
						179 155
						191 154
					yourself:
				)
		)
		(crowd init:)
		(phoneArm init:)
		(takePicRight init:)
		(girlReporter init:)
		(fatReporter init:)
		(videoCameraMan init:)
		(takePicRight init:)
		(lightLeft init:)
		(lightRight init:)
		(cameraFlasher init:)
		(cameraMan init:)
		(kid init:)
		(addToPics doit:)
		(Load PICTURE 1)
		(theMusic
			number: 294
			flags: 1
			setLoop: -1
			play: 0
			fade: 127 15 12 0
		)
		(HandsOff)
		(self setScript: sJetWay)
	)
)

(instance sJetWay of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (IsObject flash) (not (Random 0 5)))
			(CameraFlash)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(ego cycleSpeed: 9 setCycle: EndLoop self)
			)
			(2
				(TimePrint 295 0
					#at -1 20
					#dispose
				)
				(ego normalize: posn: 151 121 setHeading: 180)
				(= ticks 60)
			)
			(3
				(TimePrint 295 1 #at -1 185 #dispose)
				(lightLeft setCycle: EndLoop)
				(lightRight setCycle: EndLoop)
				(kid cue:)
				(flash init:)
				(= ticks 30)
			)
			(4
				(cameraMan cue:)
				(fatReporter cue:)
				(videoCameraMan cue:)
				(cameraFlasher cue:)
				(= ticks 70)
			)
			(5
				(TimePrint 295 2
					#at -1 185
					#width 280
					#dispose
					self
				)
			)
			(6 (= ticks 60))
			(7
				(TimePrint 295 3
					#at -1 20
					#dispose
				)
				(ego
					normalize:
					posn: 152 123
					loop: 2
					cycleSpeed: 7
					moveSpeed: 7
					setMotion: MoveTo 152 131 self
				)
			)
			(8
				(TimePrint 295 4
					#at -1 185
					#dispose
				)
				(ego setMotion: MoveTo 152 140 self)
			)
			(9 (= ticks 90))
			(10
				(TimePrint 295 5
					#at -1 20
					#dispose
					self
				)
			)
			(11 (= ticks 60))
			(12
				(TimePrint 295 6 #at -1 20 #dispose self)
			)
			(13 (= ticks 30))
			(14
				(TimePrint 295 6 #at -1 20 #dispose self)
			)
			(15 (= ticks 30))
			(16
				(TimePrint 295 0 #at -1 185 #dispose self)
			)
			(17 (= ticks 30))
			(18
				(TimePrint 295 1 #at -1 185 #dispose self)
			)
			(19
				(girlReporter setCycle: EndLoop self)
			)
			(20 (= ticks 90))
			(21
				(TimePrint 295 7 #at -1 20 #dispose self)
			)
			(22 (= ticks 30))
			(23
				(TimePrint 295 8 #at -1 185 #dispose self)
			)
			(24 (= ticks 30))
			(25
				(TimePrint 295 9 #at -1 20 #dispose self)
			)
			(26 (= ticks 30))
			(27
				(TimePrint 295 10 #at -1 185 #dispose self)
			)
			(28 (= ticks 30))
			(29
				(TimePrint 295 11 #at -1 20 #dispose self)
			)
			(30 (= seconds 3))
			(31
				(TimePrint 295 12 #at -1 185 #dispose self)
			)
			(32 (= ticks 30))
			(33
				(TimePrint 295 13 #at -1 185 #dispose self)
			)
			(34
				(ego setMotion: MoveTo 160 170 self)
			)
			(35
				(TimePrint 295 14 #at -1 185 #dispose self)
			)
			(36 (= ticks 30))
			(37
				(phoneArm setCycle: EndLoop self)
			)
			(38
				(ego
					view: 295
					setLoop: 2
					setCel: 0
					cycleSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(39
				(phoneArm dispose:)
				(ego setCycle: EndLoop self)
			)
			(40
				(ego setLoop: 3 cycleSpeed: 6)
				(self setScript: sTalk)
			)
			(41
				(TimePrint 295 15
					#at -1 15
					#width 280
					#dispose
					self
				)
			)
			(42
				(TimePrint 295 16 #at -1 185)
				(curRoom drawPic: 1 IRISIN)
				(theMusic fade: 0 15 12 1)
				(= seconds 3)
			)
			(43
				(TimePrint 295 17)
				(TimePrint 295 18)
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance sTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: ForwardCounter 2)
				(Say ego 295 19 #at -1 185 #dispose self)
			)
			(1
				(ego setCycle: 0)
				(Say Mr__President 295 20 #dispose #caller self)
			)
			(2
				(ego setCycle: ForwardCounter 5)
				(Say ego 295 21 #at -1 185 #dispose self)
			)
			(3
				(ego setCycle: 0)
				(Say Mr__President 295 22 #dispose #caller self)
			)
			(4 (= ticks 30))
			(5
				(Say Mr__President 295 23 #dispose #caller self)
			)
			(6 (= ticks 30))
			(7
				(Say Mr__President 295 24 #dispose #caller self)
			)
			(8 (= ticks 30))
			(9
				(Say Mr__President 295 25 #dispose #caller self)
			)
			(10 (= ticks 30))
			(11
				(Say Mr__President 295 26 #dispose #caller self)
			)
			(12 (= ticks 30))
			(13
				(Say Mr__President 295 27 #dispose #caller self)
			)
			(14 (= ticks 30))
			(15
				(Say Mr__President 295 28 #dispose #caller self)
			)
			(16 (= ticks 30))
			(17
				(ego setCycle: ForwardCounter 6)
				(Say ego 295 29 #at -1 185 #dispose self)
			)
			(18
				(ego setCycle: 0)
				(TimePrint 295 30 #at -1 185 #dispose self)
			)
			(19
				(ego setCycle: ForwardCounter 6)
				(Say ego 295 31 #at -1 185 #dispose self)
			)
			(20
				(ego setCycle: 0)
				(Say Mr__President 295 32 #dispose #caller self)
			)
			(21 (= ticks 30))
			(22
				(Say Mr__President 295 33 #dispose #caller self)
			)
			(23 (= ticks 30))
			(24
				(Say Mr__President 295 34 #dispose #caller self)
			)
			(25
				(TimePrint 295 35 #at -1 185 #dispose self)
			)
			(26
				(ego setCycle: ForwardCounter 5)
				(Say ego 295 36 #at -1 185 #dispose self)
			)
			(27
				(ego setCycle: 0)
				(client cue:)
			)
		)
	)
)

(instance videoCameraMan of Prop
	(properties
		x 116
		y 144
		view 297
		loop 2
		cel 8
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 13
	)
	
	(method (cue)
		(switch (++ cameraManState)
			(1
				(self setCycle: BegLoop self)
			)
			(2
				((Timer new:) setReal: self 5)
			)
			(3 (self setCycle: EndLoop self))
			(4
				(= cameraManState 0)
				((Timer new:) setReal: self (Random 5 7))
			)
		)
	)
)

(instance cameraFlasher of Prop
	(properties
		x 184
		y 129
		view 297
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(switch (++ flasherState)
			(1 (self setCycle: EndLoop self))
			(2
				(self setCel: 0)
				(self cue:)
			)
			(3
				(= flasherState 0)
				((Timer new:) setReal: self (Random 3 5))
			)
		)
	)
)

(instance cameraMan of Prop
	(properties
		x 117
		y 135
		view 297
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (cue)
		(switch (++ local3)
			(1 (self setCycle: EndLoop self))
			(2
				((Timer new:) setReal: self 2)
			)
			(3 (self setCycle: BegLoop self))
			(4
				(= local3 0)
				((Timer new:) setReal: self (Random 2 3))
			)
		)
	)
)

(instance takePicRight of PicView
	(properties
		x 203
		y 135
		view 297
		loop 4
		priority 13
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance fatReporter of Prop
	(properties
		x 197
		y 137
		view 297
		loop 5
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 15
	)
	
	(method (cue)
		(switch (++ reporterState)
			(1
				(self setCycle: EndLoop self)
			)
			(2
				(self setCycle: BegLoop self)
			)
			(3
				(= reporterState 0)
				((Timer new:) setReal: self (Random 2 4))
			)
		)
	)
)

(instance kid of Prop
	(properties
		x 179
		y 133
		view 297
		loop 6
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (cue)
		(switch (++ kidState)
			(1 (self setCycle: EndLoop self))
			(2
				(self setCel: 0)
				(self cue:)
			)
			(3 (self setCycle: EndLoop self))
			(4
				(self setCel: 0)
				(self cue:)
			)
			(5
				(= kidState 0)
				((Timer new:) setReal: self (Random 2 4))
			)
		)
	)
)

(instance girlReporter of Prop
	(properties
		x 200
		y 163
		view 297
		loop 7
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
	)
)

(instance phoneArm of Prop
	(properties
		x 127
		y 154
		view 295
		priority 12
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
	)
)

(instance flash of Prop
	(properties
		view 295
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance crowd of PicView
	(properties
		x 163
		y 159
		view 297
		signal (| ignrAct stopUpdOn)
	)
)

(instance lightLeft of Prop
	(properties
		x 91
		y 77
		view 297
		loop 8
		priority 14
		signal fixPriOn
		cycleSpeed 10
	)
)

(instance lightRight of Prop
	(properties
		x 207
		y 76
		view 297
		loop 9
		priority 14
		signal fixPriOn
		cycleSpeed 10
	)
)

(instance Mr__President of Talker
	(properties
		nsTop 15
		nsLeft 35
		view 1295
		loop 3
		viewInPrint 1
		name "Mr. President"
	)
	
	(method (init)
		(= bust bushBust)
		(= eyes bushEyes)
		(= mouth bushMouth)
		(super init: &rest)
	)
)

(instance bushBust of Prop
	(properties
		view 1295
		loop 1
	)
)

(instance bushEyes of Prop
	(properties
		nsTop 28
		nsLeft 30
		view 1295
		loop 2
		cycleSpeed 70
	)
)

(instance bushMouth of Prop
	(properties
		nsTop 31
		nsLeft 24
		view 1295
		cycleSpeed 5
	)
)
