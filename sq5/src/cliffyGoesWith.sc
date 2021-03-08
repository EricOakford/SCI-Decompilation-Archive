;;; Sierra Script 1.0 - (do not remove this comment)
(script# 243)
(include sci.sh)
(use Main)
(use eureka)
(use rm240)
(use Scaler)
(use Osc)
(use PolyPath)
(use ForCount)
(use Reverse)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	upFromThrakus 1
	putBeaInBox 2
	proc243_3 3
	openChamber 4
	pickBeaUp 5
	getBeaFromChamber 6
	putBeaOnPad 7
	spikeNailsEgo 8
	putSpikeInTank 9
	spikeEscapes 10
	getSpike 11
	useAntAcids 12
	beamToGenetix 13
	specialBeam 14
	upFromKU 21
	putOnMask 22
	talkAboutBea 23
	cliffyGoesWith 24
	bounceEgo 25
	spikeEscapes2 26
	askAboutBea 27
	goliathConv 28
	upFromThrakus2 29
	funnyBeam 30
	upFromKU2 31
)

(local
	local0
)
(procedure (proc243_3)
)

(instance cliffyGoesWith of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 5 2 7 0 self))
			(1
				(proc240_28 1)
				((ScriptID 240 21) setMotion: MoveTo 179 164 self)
			)
			(2
				((ScriptID 240 21) setMotion: MoveTo 176 152 self)
			)
			(3
				((ScriptID 240 21) setMotion: MoveTo 121 148 self)
			)
			(4
				((ScriptID 240 21) setHeading: 180 self)
			)
			(5
				((ScriptID 240 21)
					view: 33
					loop: 0
					cel: ((ScriptID 240 21) lastCel:)
				)
				(self dispose:)
			)
		)
	)
)

(instance spikeNailsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(theMusic1 number: 23 loop: -1 play:)
				((ScriptID 240 8)
					init: 1
					setCycle: Walk
					setScale: Scaler 85 50 144 122
					detailLevel: 0
					setMotion: MoveTo (+ (ego x?) 30) (+ (ego y?) 5) self
				)
			)
			(1
				(ego setHeading: 135 self)
				((ScriptID 240 8) setHeading: 270)
			)
			(2
				((ScriptID 240 8) dispose:)
				(= register 3)
				(= start 0)
				(ego
					view: 192
					loop: 0
					cel: 0
					setScale: Scaler 116 71 149 123
					setCycle: End self
				)
				(theMusic2 number: 246 loop: 1 play:)
			)
			(3 (ego setCycle: CT 5 -1 self))
			(4
				(ego setCycle: End self)
				(if (-- register) (= state (- state 2)))
			)
			(5
				(theMusic2 number: 2471 loop: -1 play:)
				(ego y: 1 cel: 0)
				(= cycles 1)
			)
			(6 (ego setCycle: CT 4 1 self))
			(7
				(ego setCycle: CT 1 -1 self)
				(if (-- register) (= state (- state 2)))
			)
			(8
				(ego setCycle: End self)
				(theMusic2 number: 247 loop: 1 play:)
			)
			(9
				(ego view: 192 loop: 2 cel: 0 setCycle: End self)
			)
			(10
				(NormalEgo 0 4)
				(= global126 6)
				(ego get: 8)
				(if (== client curRoom)
					(proc240_30)
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance useAntAcids of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 223 146 self)
			)
			(2
				(if (Btst 43)
					(ego setHeading: 45 self)
				else
					(self setScript: (ScriptID 240 9) self)
				)
			)
			(3
				(SolvePuzzle 171 20)
				(ego
					view: 243
					loop: 0
					cel: 0
					looper: 0
					setCycle: End self
				)
			)
			(4
				(ego setCycle: Beg)
				((ScriptID 240 20)
					init:
					view: 244
					loop: 6
					cel: 0
					x: 251
					y: 100
					setPri: 12
					setCycle: End self
				)
				(theMusic2 number: 137 loop: 1 play:)
				((ScriptID 240 8) setCycle: 0)
			)
			(5
				(theMusic2 number: 137 loop: 1 play: self)
			)
			(6
				(theMusic2 number: 256 loop: 1 play:)
				((ScriptID 240 20)
					x: 244
					y: 101
					loop: 8
					cel: 0
					x: 254
					y: 101
					setCycle: End self
				)
				(bubbles2 init: setCycle: End self)
			)
			(7)
			(8
				((ScriptID 240 20) loop: 10 cel: 0 setCycle: Fwd)
				(bubbles2 loop: 9 setCycle: Fwd)
				(Bset 56)
				(theMusic2 stop:)
				(self setScript: (ScriptID 240 9) self)
			)
			(9
				(messager say: 22 29 0 1 self)
			)
			(10
				(if (cast contains: (ScriptID 240 21))
					(messager say: 22 29 0 2 self)
				else
					(= cycles 1)
				)
			)
			(11
				(theGame handsOn:)
				((ScriptID 240 8) setCycle: Fwd)
				(NormalEgo 0 6)
				(ego put: 5)
				(bubbles2 dispose:)
				((ScriptID 240 20)
					view: 244
					loop: 3
					cel: 0
					x: 254
					y: 101
					dispose:
				)
				(self dispose:)
			)
		)
	)
)

(instance bubbles2 of Prop
	(properties
		x 252
		y 102
		view 244
		loop 7
		priority 10
		signal $4010
	)
)

(instance putSpikeInTank of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== client curRoom) (theGame handsOff:))
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(if (Btst 43)
					(= cycles 1)
				else
					(self setScript: (ScriptID 240 9) self)
				)
			)
			(2
				(ego setMotion: PolyPath 214 141 self)
			)
			(3 (ego setHeading: 180 self))
			(4
				(ego view: 192 loop: 2 cel: 15 setCycle: Beg self)
			)
			(5
				((ScriptID 240 27) noun: 25)
				(ego
					view: 243
					loop: 1
					cel: 0
					setScale: Scaler 116 71 149 123
					posn: 220 141
					setCycle: End self
				)
			)
			(6
				(SolvePuzzle 169 20)
				(NormalEgo 0 4)
				(ego setMotion: PolyPath 210 141 self)
				(= global126 2)
				((ScriptID 240 8) init: detailLevel: 3)
				(ego put: 8)
			)
			(7
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance spikeEscapes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global126 5)
				((ScriptID 240 8) detailLevel: 0 setCycle: Beg self)
			)
			(1
				(if (not (Btst 43))
					(= register 0)
					(extra2
						view: 244
						loop: 1
						cel: 0
						init:
						posn: 254 101
						setPri: 15
						setCycle: End self
					)
				else
					(= register 1)
					(= cycles (++ state))
				)
			)
			(2
				(extra2 setCycle: Beg self)
				((ScriptID 240 24) init: 1 setCycle: End self)
				(Bset 43)
				((ScriptID 240 20) init: cel: 0 setCycle: End)
			)
			(3
				(Face ego (ScriptID 240 8))
				(if (cast contains: (ScriptID 240 21))
					(= local0 ((ScriptID 240 21) view?))
					(proc240_28 7)
					(Face (ScriptID 240 21) (ScriptID 240 8))
				)
				((ScriptID 240 24) stopUpd:)
				(extra2 dispose:)
				((ScriptID 240 8)
					view: 244
					loop: 5
					cel: 0
					posn: 251 100
					setCycle: End self
				)
			)
			(4
				((ScriptID 240 20) dispose:)
				(theMusic1 number: 23 loop: -1 play:)
				(proc240_29 1)
				((ScriptID 240 8)
					posn: 181 144
					setMotion: MoveTo 181 189 self
				)
				(if (cast contains: (ScriptID 240 21))
					(Face (ScriptID 240 21) (ScriptID 240 8))
				)
			)
			(5
				(if (cast contains: (ScriptID 240 21))
					(Face (ScriptID 240 21) (ScriptID 240 8))
				)
				(Face ego (ScriptID 240 8) self)
			)
			(6
				(if register
					(messager say: 22 0 42 0 self)
				else
					(messager say: 22 0 43 0 self)
				)
			)
			(7
				(if (cast contains: (ScriptID 240 21))
					(if register
						(messager say: 5 0 42 0 self)
					else
						(messager say: 5 0 43 0 self)
					)
				)
				(= cycles 1)
			)
			(8
				(if (cast contains: (ScriptID 240 21))
					((ScriptID 240 21) setHeading: 0 self)
				else
					(= cycles 1)
				)
			)
			(9
				(if (cast contains: (ScriptID 240 21))
					(if (== local0 248)
						((ScriptID 240 21)
							view: 248
							loop: 0
							cel: 0
							x: 226
							y: 170
							setPri: 13
							setScale: Scaler 116 71 149 123
							setCycle: Fwd
							setScript: (ScriptID 240 23)
						)
					else
						((ScriptID 240 21)
							view: 191
							loop: 0
							x: 239
							y: 164
							setPri: 13
							setScale: Scaler 116 71 149 123
							setScript: (ScriptID 240 23)
							setCycle: Fwd
						)
					)
				)
				(proc240_30)
				((ScriptID 240 8) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance spikeEscapes2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== client curRoom) (theGame handsOff:))
				(= global126 5)
				(ego setHeading: 180 self)
			)
			(1
				(ego view: 246 loop: 0 cel: 0 put: 8 setCycle: End self)
			)
			(2
				(NormalEgo 0 2)
				(theMusic1 number: 23 loop: -1 play:)
				(proc240_29 1)
				((ScriptID 240 8)
					posn: (- (ego x?) 21) (+ (ego y?) 1)
					init: 1
					setMotion: MoveTo 79 187 self
				)
			)
			(3
				(if (== client curRoom) (theGame handsOn:))
				(proc240_30)
				((ScriptID 240 8) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance upFromThrakus2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc240_30)
				(proc240_1 1)
				(ego
					setScale: 0
					init:
					get: 21
					view: 22
					loop: 0
					posn: 106 135
					cel: 0
					setCycle: End self
				)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(1
				(theMusic2 stop:)
				(ego loop: 1 cel: (ego lastCel:) setCycle: Beg self)
			)
			(2
				(NormalEgo 0 2)
				(Bclr 109)
				(ego setScale: 0 posn: 109 135)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance putOnMask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (proc240_1))
						(messager say: 19 39 27 0)
						(theGame handsOn:)
						(self dispose:)
					)
					(
						(or
							(and
								(cast contains: (ScriptID 240 2))
								(!= global164 9)
							)
							(proc240_7)
							(and
								(Btst 63)
								(Btst 45)
								(not (OneOf global164 1 8))
								(== eurekaCurLocation 6)
							)
							(and
								(!= prevRoomNum 225)
								(== eurekaCurLocation 8)
								(!= global164 8)
							)
						)
						(messager say: 26 24 13 0)
					)
					(else
						(theGame handsOff:)
						(SolvePuzzle 183 20)
						(Bset 63)
						(Bset 109)
						(ego view: 22 loop: 1 cel: 0 setCycle: End self)
					)
				)
			)
			(1 (= seconds 3))
			(2
				(ego
					loop: 0
					put: 21
					cel: (ego lastCel:)
					setCycle: Beg self
				)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(3
				(ego dispose:)
				(= seconds 2)
			)
			(4
				(theMusic2 stop:)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance getSpike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== (eureka puke?) 7) (SolvePuzzle 203 10))
				((ScriptID 240 27) noun: 24)
				((ScriptID 240 8) setCycle: Beg self)
			)
			(1
				((ScriptID 240 8) view: 244 loop: 5 cel: 0 posn: 251 100)
				(ego
					view: 0
					looper: 0
					setCycle: Rev
					setLoop: 0
					setMotion: MoveTo 191 144 self
				)
			)
			(2
				(ego setCycle: 0)
				((ScriptID 240 20) dispose:)
				((ScriptID 240 8) setCycle: CT 5 1 self)
			)
			(3
				(spikeNailsEgo start: 2)
				(= next spikeNailsEgo)
				(self dispose:)
			)
		)
	)
)

(instance spikeSayNo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 240 8)
					view: 245
					loop: 5
					cel: 0
					setCycle: End self
				)
			)
			(1
				((ScriptID 240 8)
					view: 245
					loop: 6
					cel: 0
					setCycle: ForwardCounter 5 self
				)
			)
			(2
				((ScriptID 240 8) view: 245 loop: 0 cel: 0)
				(self dispose:)
			)
		)
	)
)

(instance talkAboutBea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 103)
				(= global169 (| global169 $0002))
				(= global130 5)
				(proc240_1 1)
				(ego
					setScale: Scaler 116 71 149 123
					init:
					view: 6
					loop: 0
					posn: 106 135
					cel: 0
					setCycle: End self
				)
				(theMusic2 number: 260 loop: 1 play:)
				((ScriptID 240 21) setCycle: End self)
			)
			(1)
			(2
				(theMusic2 stop:)
				(proc240_28 1)
				((ScriptID 240 21) setMotion: MoveTo 214 145 self)
				(NormalEgo 0 2)
				(ego setScale: 0 posn: 109 135 setSpeed: 6)
				(self setScript: (ScriptID 240 3) self 0)
			)
			(3)
			(4
				(ego setMotion: PolyPath 136 129 self)
			)
			(5
				(ego setHeading: 135 self)
				((ScriptID 240 21) setHeading: 0 self)
			)
			(6)
			(7
				(messager say: 15 0 0 1 2 self)
			)
			(8
				((ScriptID 240 8) setCycle: Beg)
				((ScriptID 240 21) setHeading: 90 self)
			)
			(9
				(theMusic1 number: 23 loop: -1 play:)
				(messager say: 15 0 0 3 self)
			)
			(10
				((ScriptID 240 21) setHeading: 0 self)
			)
			(11
				((ScriptID 240 21) setMotion: MoveTo 238 162 self)
			)
			(12
				((ScriptID 240 21) setHeading: 0)
				((ScriptID 240 24) init: 1 setCycle: End)
				((ScriptID 240 20) dispose:)
				((ScriptID 240 8)
					view: 244
					loop: 5
					cel: 0
					posn: 251 100
					setCycle: End self
				)
				(theMusic2 number: 246 loop: 1 play:)
			)
			(13
				((ScriptID 240 21)
					view: 26
					loop: 0
					cel: 0
					setScript: (ScriptID 240 23)
				)
				((ScriptID 240 24) stopUpd:)
				(messager say: 15 0 0 4 self)
			)
			(14
				(proc240_29 1)
				((ScriptID 240 8)
					posn: 181 144
					setMotion: MoveTo 202 137 self
				)
			)
			(15
				(Bclr 43)
				((ScriptID 240 24) setCycle: Beg)
				((ScriptID 240 27) noun: 24)
				((ScriptID 240 8)
					view: 245
					loop: 0
					cel: 0
					posn: 197 139
					setPri: 14
					setCycle: End
					setMotion: JumpTo 185 115 self
				)
				(theMusic2 number: 246 loop: 1 play:)
			)
			(16
				((ScriptID 240 8) setCycle: ForwardCounter 5 self)
				(theMusic2 number: 246 loop: -1 play:)
			)
			(17
				(theMusic2 stop:)
				(messager say: 15 0 0 5 6 self)
			)
			(18
				((ScriptID 240 8)
					setCycle: End
					setMotion: JumpTo 159 140 self
				)
				(theMusic2 number: 246 loop: 1 play:)
			)
			(19
				(Face ego (ScriptID 240 8))
				((ScriptID 240 8)
					setPri: -1
					setCycle: End
					setMotion: JumpTo 125 142 self
				)
				(theMusic2 number: 246 loop: 1 play:)
			)
			(20
				(Face ego (ScriptID 240 8))
				((ScriptID 240 8) setCycle: ForwardCounter 5 self)
				(theMusic2 number: 246 loop: -1 play:)
			)
			(21
				(theMusic2 stop:)
				((ScriptID 240 8)
					loop: 2
					cel: 0
					setCycle: ForwardCounter 3 self
				)
			)
			(22
				((ScriptID 240 8)
					loop: 4
					cel: 0
					setCycle: ForwardCounter 3 self
				)
			)
			(23
				(messager say: 15 0 0 7 8 self)
			)
			(24
				(proc240_29 1)
				((ScriptID 240 8)
					posn: 129 137
					setMotion: MoveTo 163 137 self
				)
			)
			(25
				(Face ego (ScriptID 240 8))
				((ScriptID 240 8) setMotion: MoveTo 202 137 self)
			)
			(26
				(Face ego (ScriptID 240 21))
				((ScriptID 240 8)
					view: 245
					loop: 0
					cel: 0
					posn: 197 139
					setPri: 10
					setCycle: End
					setMotion: JumpTo 185 115 self
				)
				(theMusic2 number: 246 loop: 1 play:)
				(= global126 7)
			)
			(27
				(= next askAboutBea)
				(self dispose:)
			)
		)
	)
)

(instance askAboutBea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1 number: 23 loop: -1 play:)
				((ScriptID 240 21)
					view: 26
					loop: 0
					setScript: (ScriptID 240 23)
				)
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 136 129 self)
			)
			(2 (ego setHeading: 135 self))
			(3
				(messager say: 15 0 0 9 self)
			)
			(4
				(theGame handsOn:)
				(theGame setCursor: 999 1)
				((ScriptID 240 15)
					normal: 0
					curNoun: 15
					curVerb: 0
					curCase: 20
				)
				(messager say: 15 0 20 1 self)
			)
			(5
				(theGame handsOff:)
				((ScriptID 240 15) normal: 1)
				(cond 
					((not ((ScriptID 240 15) whichSelect?)) (= state (- state 2)) (= cycles 1))
					((== ((ScriptID 240 15) whichSelect?) 4)
						((ScriptID 240 8) setCycle: ForwardCounter 5 self)
						(theMusic2 number: 246 loop: -1 play:)
					)
					(else (self setScript: spikeSayNo self))
				)
			)
			(6
				(theMusic2 stop:)
				((ScriptID 240 15) normal: 1)
				(messager
					say:
						15
						0
						(switch ((ScriptID 240 15) whichSelect?)
							(1 21)
							(2 22)
							(3 23)
							(4 24)
						)
						0
						self
				)
			)
			(7
				(if (< ((ScriptID 240 15) whichSelect?) 4)
					(theGame handsOn:)
					(= next 0)
					(ego setSpeed: egoSpeed)
					(self dispose:)
				else
					((ScriptID 240 8)
						loop: 0
						cel: 0
						setCycle: End
						setMotion: JumpTo 155 109 self
					)
					(theMusic2 number: 246 loop: 1 play:)
				)
			)
			(8
				(spikeNailsEgo start: 2)
				(self setScript: spikeNailsEgo self)
			)
			(9
				(self setScript: putSpikeInTank self)
			)
			(10
				(= global130 12)
				(ego setSpeed: egoSpeed)
				(theMusic1 fade: 0 10 5 1)
				(self setScript: (ScriptID 240 9) self)
			)
			(11
				(proc240_30)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance upFromThrakus of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc240_1 1)
				(cast eachElementDo: #stopUpd)
				(= seconds 3)
			)
			(1
				(ego
					init:
					get: 21
					setScale: 0
					cycleSpeed: 10
					view: 23
					loop: 0
					cel: 0
					posn: 124 135
					setCycle: End self
				)
				(Bclr 109)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(2
				((ScriptID 240 21) view: 26 loop: 0 cel: 0 stopUpd:)
				(theMusic2 stop:)
				(ego loop: 1 cel: 0 posn: 115 140 setCycle: End self)
			)
			(3
				(ego loop: 2 cel: 0 posn: 112 136 setCycle: End self)
			)
			(4
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(5
				(cast eachElementDo: #hide)
				(theMusic1 number: 33 loop: -1 play:)
				(ego ignoreActors: 1)
				(DrawPic 46 dpOPEN_FADEPALETTE TRUE)
				(= seconds 3)
			)
			(6
				(proc240_25 1)
				(proc240_26 1)
				(messager say: 1 2 1 1 10 self)
			)
			(7
				(beaArm init:)
				(= seconds 5)
			)
			(8
				(messager say: 1 2 1 11 13 self)
			)
			(9
				(proc240_25 0)
				(proc240_26 0)
				(theMusic1 fade: 0 10 5 1 self)
				(cast eachElementDo: #show)
				(ego ignoreActors: 0)
				(beaArm dispose:)
				(DrawPic 43 dpOPEN_FADEPALETTE TRUE)
				(addToPics eachElementDo: #init)
			)
			(10
				(proc240_30)
				(messager say: 1 2 1 14 17 self)
			)
			(11
				(ego
					view: 256
					loop: 0
					get: 3
					cel: 4
					posn: 121 138
					setCycle: Beg self
				)
			)
			(12
				((ScriptID 240 2) init:)
				(NormalEgo 0 2)
				(ego posn: 123 137 setScale: Scaler 116 71 149 123)
				(= cycles 2)
			)
			(13
				((ScriptID 240 2) stopUpd:)
				(proc240_6 180 141)
				(= next (ScriptID 240 3))
				((ScriptID 240 3) register: 2)
				(cast eachElementDo: #startUpd)
				(cast eachElementDo: #checkDetail)
				(self dispose:)
			)
		)
	)
)

(instance upFromGenetix of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc240_1 1)
				(= seconds 3)
			)
			(1
				(proc240_6 180 141)
				(= next (ScriptID 240 3))
				((ScriptID 240 3) register: 2)
				(self dispose:)
			)
		)
	)
)

(instance putBeaOnPad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if (proc240_1)
					(= cycles 1)
				else
					(if
					(and (== (ego view?) 257) (== (ego loop?) 1))
						(ego posn: 158 136)
					)
					(ego
						view: 258
						looper: 0
						setLoop: 1
						cel: 0
						setCycle: Walk
						setMotion: MoveTo 119 138 self
					)
				)
			)
			(1
				1
				(ego
					view: 256
					looper: 0
					loop: 0
					cel: 7
					x: 121
					y: 138
					setCycle: CT 4 -1 self
				)
			)
			(2
				2
				((ScriptID 240 2) init:)
				(proc240_7 0)
				(ego setCycle: Beg self)
			)
			(3
				3
				(proc240_1 1)
				(NormalEgo 0 2)
				(ego posn: 123 137 setScale: 0)
				(if (== global130 12)
					(= next cureBea)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance cureBea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global164 8)
				((ScriptID 240 2) stopUpd:)
				((ScriptID 240 21) startUpd:)
				(self setScript: (ScriptID 240 3) self 2)
			)
			(1 (ego setHeading: 270 self))
			(2
				(messager say: 15 0 25 1 2 self)
			)
			(3
				(SolvePuzzle 188 50)
				(proc240_28 3)
				((ScriptID 240 21) setHeading: 90 self)
			)
			(4
				((ScriptID 240 21)
					view: 191
					loop: 4
					x: 239
					y: 164
					setPri: 13
					setScale: Scaler 116 71 149 123
					setCycle: End self
				)
			)
			(5
				((ScriptID 240 21) setCycle: End self)
				(theMusic2 number: 233 loop: 1 play:)
			)
			(6
				((ScriptID 240 21) setCycle: End self)
				(theMusic2 number: 233 loop: 1 play:)
			)
			(7
				(theMusic2 number: 233 loop: 1 play:)
				(proc240_28 0)
				((ScriptID 240 21) posn: 238 162 setHeading: 325 self)
			)
			(8
				((ScriptID 240 21) stopUpd:)
				((ScriptID 240 2)
					view: 256
					loop: 2
					cel: ((ScriptID 240 2) lastCel:)
					x: 119
					y: 183
					setCycle: Beg self
				)
				(theMusic2 number: 260 loop: 1 play: self)
			)
			(9 ((ScriptID 240 2) hide:))
			(10
				((ScriptID 240 2) show: setCycle: End self)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(11
				(theMusic2 stop:)
				((ScriptID 240 2) loop: 1 cel: 0 posn: 97 186 stopUpd:)
				(self setScript: (ScriptID 240 4) self 2)
			)
			(12
				(ego setMotion: MoveTo 123 137 self)
			)
			(13
				((ScriptID 240 2) dispose:)
				(ego
					view: 256
					loop: 0
					cel: 0
					x: 121
					y: 138
					setCycle: CT 3 1 self
				)
			)
			(14
				(theMusic1 number: 34 loop: -1 play:)
				(cast eachElementDo: #hide)
				(ego ignoreActors: 1)
				(DrawPic 46 dpOPEN_FADEPALETTE TRUE)
				(= seconds 3)
			)
			(15
				(proc240_26 1)
				(proc240_25 1)
				(messager say: 15 0 25 3 6 self)
			)
			(16
				(theMusic2 number: 502 loop: 1 play:)
				(messager say: 15 0 44 0 self)
			)
			(17
				(messager say: 15 0 25 7 self)
			)
			(18
				(proc240_26 0)
				(proc240_25 0)
				(cast eachElementDo: #show)
				(= global164 8)
				(= global130 2)
				(ego ignoreActors: 0)
				(DrawPic 43 dpOPEN_FADEPALETTE TRUE)
				(addToPics eachElementDo: #init)
				(theMusic1 fade: 0 10 5 1 self)
			)
			(19
				(proc240_30)
				(pickBeaUp start: 3)
				(= next pickBeaUp)
				(self dispose:)
			)
		)
	)
)

(instance pickBeaUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (proc240_1))
					(self setScript: (ScriptID 240 4) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 123 137 self)
			)
			(2
				(ego
					view: 256
					loop: 0
					cel: 0
					x: 121
					y: 138
					setCycle: CT 3 1 self
				)
			)
			(3
				((ScriptID 240 2) dispose:)
				(ego setCycle: End self)
			)
			(4
				(proc240_1 0)
				(ego
					looper: 0
					view: 258
					setLoop: 0
					cel: 3
					setCycle: Walk
					setMotion: MoveTo 174 135 self
				)
			)
			(5
				(ego setCel: 3)
				(proc240_7 1)
				(if (== start 3)
					(= start 0)
					(= next putBeaInBox)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance putBeaInBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== (ego loop?) 1)
					(ego setCycle: Beg self)
				else
					(ego
						view: 257
						setLoop: 0
						cel: 0
						posn: 183 125
						setScale: 0
						setPri: 15
						setCycle: End self
					)
				)
			)
			(1
				(SolvePuzzle 185 20)
				(NormalEgo 0 0)
				(ego setScale: Scaler 116 71 149 123 x: 160 y: 136)
				((ScriptID 240 5)
					view: 280
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 240 21)
					view: 191
					loop: 0
					x: 239
					y: 164
					setPri: 13
					setScale: Scaler 116 71 149 123
					setScript: (ScriptID 240 23)
				)
				(Bset 45)
				(proc240_7 0)
				((ScriptID 240 5) init:)
				(cast eachElementDo: #checkDetail)
				(UnLoad 128 280)
				(UnLoad 128 257)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance goliathConv of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 225 146 self)
			)
			(1 (ego setHeading: 180 self))
			(2
				(if (& global169 $0008)
					((ScriptID 240 11)
						normal: 0
						curNoun: 1
						curVerb: 2
						curCase: 4
					)
					(theGame handsOn:)
					(theGame setCursor: 999 1)
					(messager say: 1 2 4 1 self 246)
				else
					(= state (+ state 2))
					(= cycles 1)
				)
			)
			(3
				(theGame handsOff:)
				((ScriptID 240 11) normal: 1)
				(switch ((ScriptID 240 11) whichSelect?)
					(1
						(messager say: 1 2 1 0 self 246)
					)
					(2
						(messager say: 1 2 2 0 self 246)
					)
					(3
						(messager say: 1 2 3 0 self 246)
					)
					(else 
						(= state (- state 2))
						(= cycles 1)
					)
				)
			)
			(4
				(if (!= ((ScriptID 240 11) whichSelect?) 1)
					(theGame handsOn:)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(5 (curRoom newRoom: 246))
		)
	)
)

(instance openChamber of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 192 135 self)
			)
			(2 (ego setHeading: 0 self))
			(3
				(cast eachElementDo: #stopUpd)
				(NormalEgo 0 3)
				(if
					(and
						(not (Btst 45))
						(== eurekaCurLocation 6)
						(!= prevRoomNum 225)
					)
					(SolvePuzzle 184 20)
				)
				((ScriptID 240 5)
					view: 280
					loop: (if (Btst 45) 1 else 0)
					cel: (if (Btst 45) 5 else 0)
					setCycle: (if (Btst 45) Beg else End) self
				)
			)
			(4
				(if (not ((ScriptID 240 5) loop?))
					((ScriptID 240 5) view: 259 loop: 2 setCel: 2)
				)
				(ego setMotion: MoveTo 167 134 self)
			)
			(5
				(cast eachElementDo: #checkDetail)
				((ScriptID 240 5) stopUpd:)
				(UnLoad 128 280)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getBeaFromChamber of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 167 134 self)
			)
			(2
				((ScriptID 240 5) view: 259 loop: 2 cel: 2)
				(= cycles 2)
			)
			(3
				(cast eachElementDo: #stopUpd)
				(Bclr 45)
				(ego
					view: 257
					looper: 0
					setLoop: 1
					cel: 0
					x: 178
					y: 132
					setScale: 0
					setCycle: End self
				)
			)
			(4
				((ScriptID 240 5) stopUpd:)
				(proc240_7 1)
				(if (== global164 1)
					(= next breakBea)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance breakBea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 257
					loop: 2
					cel: 0
					x: 161
					y: 109
					setPri: 14
					setCycle: End self
				)
				(theMusic2 number: 267 loop: 1 play: self)
			)
			(1)
			(2 (messager say: 2 0 2 0 self))
			(3 (EgoDead 47))
		)
	)
)

(instance beamToGenetix of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== eurekaCurLocation 14)
					(theGame handsOff:)
				else
					(Bset 75)
				)
				(self setScript: blowFuse self)
				(ego setPri: 15 setCycle: Osc)
				(if (== global164 9)
					((ScriptID 240 2) setPri: 15 setCycle: Osc)
				)
			)
			(1
				(if (== eurekaCurLocation 14)
					(Bset 72)
					(Bclr 73)
					(ego setCycle: End self)
					(if (== global164 9) ((ScriptID 240 2) setCycle: End))
				else
					(ego setCycle: Beg self)
				)
			)
			(2
				(if (== eurekaCurLocation 14)
					(messager say: 26 24 46 0 self)
				else
					(curRoom newRoom: 760)
				)
			)
			(3
				(theGame handsOn:)
				(if (== global164 9) ((ScriptID 240 2) setPri: -1))
				(extra1 dispose:)
				(extra2 dispose:)
				(extra3 dispose:)
				(NormalEgo 0 2)
				(self dispose:)
			)
		)
	)
)

(instance bounceEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego show: setCycle: Osc)
				(theMusic2 number: 260 loop: -1 play:)
				(= seconds 4)
			)
			(1 (ego setCycle: End self))
			(2
				(theMusic2 stop:)
				(cond 
					((== global130 8)
						(if (not (& global169 $0010))
							(= global169 (| global169 $0010))
							(messager say: 4 0 0 0 self 246)
						else
							(messager say: 4 0 5 0 self 246)
						)
					)
					((cast contains: (ScriptID 240 21)) (messager say: 26 24 37 0 self))
					(else (messager say: 26 24 54 0 self))
				)
			)
			(3
				(theGame handsOn:)
				(NormalEgo 0 2)
				(proc240_1 1)
				(ego heading: 180)
				(self dispose:)
			)
		)
	)
)

(instance blowFuse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 145 loop: -1 play:)
				(extra3
					view: 253
					loop: 3
					cel: 0
					x: 135
					y: 174
					init:
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(1
				(extra1
					view: 253
					loop: 0
					cel: 0
					x: 126
					y: 164
					init:
					setCycle: Fwd
				)
				(extra2
					view: 253
					loop: 1
					cel: 0
					x: 119
					y: 146
					init:
					setCycle: Fwd
				)
				(= seconds 4)
			)
			(2
				(extra1
					view: 253
					loop: 2
					cel: 0
					x: 98
					y: 134
					setPri: 15
					setCycle: Fwd
				)
				(extra2
					view: 253
					loop: 2
					cel: 0
					x: 119
					y: 137
					setPri: 15
					setCycle: Fwd
				)
				(extra3
					view: 253
					loop: 2
					cel: 0
					x: 135
					y: 134
					setPri: 15
					setCycle: Fwd
				)
				(theMusic2 client: 0 stop:)
				(self dispose:)
			)
		)
	)
)

(instance specialBeam of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 225)
					(= state (+ state 1))
					(= cycles 1)
				else
					(proc240_1 1)
					(ego
						setScale: Scaler 116 71 149 123
						init:
						view: 6
						loop: 0
						posn: 106 135
						cel: 0
						setCycle: End self
					)
					(theMusic2 number: 260 loop: 1 play:)
				)
			)
			(1
				(theMusic2 stop:)
				(NormalEgo 0 2)
				(ego setScale: 0 posn: 109 135)
				(self setScript: (ScriptID 240 3) self 1)
			)
			(2 (ego setHeading: 90 self))
			(3
				(messager say: 28 0 40 1 self)
			)
			(4
				(ego
					view: 260
					loop: 0
					cel: 0
					put: 20
					setCycle: CT 3 1 self
				)
			)
			(5
				(extra1
					init:
					view: 260
					loop: 1
					cel: 3
					setCycle: Fwd
					setPri: 12
					posn: (+ (ego x?) 16) (- (ego y?) 39)
					setMotion: JumpTo 259 109 self
				)
				(ego setCycle: End self)
			)
			(6)
			(7
				(theMusic2 number: 233 loop: 1 play:)
				(NormalEgo 0 0)
				(extra1 setMotion: JumpTo 227 131 self)
			)
			(8
				(theMusic2 number: 233 loop: 1 play:)
				(messager say: 28 0 40 2 3 self)
				(extra1 dispose:)
			)
			(9
				(ego setMotion: PolyPath 121 127 self)
				(proc240_28 3)
				((ScriptID 240 21) setMotion: MoveTo 168 160 self)
			)
			(10)
			(11
				(ego setHeading: 135)
				((ScriptID 240 21) setMotion: MoveTo 147 130 self)
			)
			(12
				(ego
					view: 248
					loop: 6
					cel: 0
					x: 129
					y: 130
					setCycle: End self
				)
				((ScriptID 240 21) hide:)
			)
			(13
				(messager say: 28 0 40 4 5 self)
			)
			(14
				((ScriptID 240 21) show: setMotion: MoveTo 168 160 self)
				(NormalEgo 0 2)
				(ego get: 6)
			)
			(15
				((ScriptID 240 21) setMotion: MoveTo 226 170 self)
			)
			(16
				((ScriptID 240 21)
					view: 248
					loop: 0
					cel: 0
					x: 226
					y: 170
					setPri: 13
					setScript: (ScriptID 240 23)
				)
				(= cycles 5)
			)
			(17
				((ScriptID 240 21) detailLevel: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance upFromKUEgoAlone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 260 loop: 1 play:)
				(ego
					init:
					view: 21
					setLoop: 9
					cel: 0
					x: 91
					y: 77
					scaleSignal: 1
					scaleX: 120
					scaleY: 120
					cycleSpeed: 6
					moveSpeed: 2
					setStep: 2 6
					setPri: 14
					setCycle: End self
				)
			)
			(1
				(ego
					setLoop: 2
					cel: 0
					x: 107
					y: 108
					setMotion: MoveTo 107 135 self
				)
			)
			(2
				(theMusic2 number: 233 loop: 1 play:)
				(ego setCycle: End)
				(proc240_28 1)
				((ScriptID 240 21) setMotion: MoveTo 179 164 self)
			)
			(3
				((ScriptID 240 21) setMotion: MoveTo 159 139 self)
			)
			(4
				((ScriptID 240 21) setMotion: MoveTo 137 136 self)
			)
			(5
				((ScriptID 240 21) setHeading: 225 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance upFromKUEgoAndCliffy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 260 loop: 1 play:)
				(ego
					init:
					view: 21
					setLoop: 9
					cel: 0
					x: 91
					y: 77
					scaleSignal: 1
					scaleX: 120
					scaleY: 120
					cycleSpeed: 6
					moveSpeed: 2
					setPri: 14
					setStep: 2 6
					setCycle: End self
				)
				((ScriptID 240 21) setCycle: End self)
			)
			(1)
			(2
				(ego
					setLoop: 2
					cel: 0
					x: 107
					y: 108
					setMotion: MoveTo 107 135 self
				)
				((ScriptID 240 21)
					setLoop: 10
					cel: 0
					x: 105
					y: 108
					moveSpeed: 2
					setStep: 2 6
					setPri: 14
					setMotion: MoveTo 105 135 self
				)
			)
			(3)
			(4
				(theMusic2 number: 233 loop: 1 play:)
				(ego setCycle: End self)
				((ScriptID 240 21) setCycle: End self)
			)
			(5)
			(6
				((ScriptID 240 21)
					setLoop: 4
					cel: 0
					x: 136
					y: 136
					setCycle: End self
				)
			)
			(7
				((ScriptID 240 21)
					setLoop: 6
					cel: 0
					x: 136
					y: 136
					setCycle: End self
				)
			)
			(8 (self dispose:))
		)
	)
)

(instance upFromKU of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 89)
				(ego put: 12)
				(if (& global169 $0020)
					(self setScript: upFromKUEgoAlone self)
				else
					(self setScript: upFromKUEgoAndCliffy self)
				)
			)
			(1
				(messager say: 26 0 34 0 self)
			)
			(2
				((ScriptID 240 21) hide:)
				(ego y: 7 cel: 0 setCycle: End self)
			)
			(3 (ego setCycle: CT 2 -1 self))
			(4
				(if (-- register) (= state (- state 2)))
				(ego setCycle: End self)
			)
			(5
				(theMusic2 number: 136 loop: 1 play:)
				(ego loop: 8 cel: 0 setCycle: End self)
			)
			(6
				(NormalEgo 0 3)
				(ego posn: 109 135 setScale: Scaler 116 71 149 123)
				(proc240_28 2)
				((ScriptID 240 21)
					show:
					setStep: 5 2
					setScale: Scaler 116 71 149 123
				)
				(if (== wd40State 1)
					(= global130 4)
					(messager say: 26 0 36 0 self)
				else
					(= global130 1)
					(messager say: 26 0 35 0 self)
				)
			)
			(7
				(self setScript: (ScriptID 240 3) self 1)
			)
			(8
				(self
					setScript:
						(if (== wd40State 1)
							cliffyGoesBackToWD40
						else
							cliffyLeavesThruDoor
						)
						self
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance upFromKU2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc240_1 1)
				(ego
					setScale: Scaler 116 71 149 123
					init:
					view: 6
					loop: 0
					posn: 109 135
					cel: 0
					setCycle: End self
				)
				(theMusic2 number: 260 loop: 1 play:)
				((ScriptID 240 21) init: setCycle: End self)
				(if (== eurekaCurLocation 3)
					(= global169 (| global169 $0020))
				)
			)
			(1)
			(2
				(NormalEgo 0 2)
				(ego posn: 109 135 setScale: Scaler 116 71 149 123)
				(proc240_28 2)
				((ScriptID 240 21) show: posn: 122 147)
				(if (== wd40State 1)
					(= global130 4)
				else
					(= global130 2)
				)
				(= cycles 2)
			)
			(3
				(self setScript: (ScriptID 240 3) self 1)
			)
			(4
				(self setScript: cliffyGoesBackToWD40 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cliffyLeavesThruDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 240 21) setMotion: MoveTo 163 143 self)
			)
			(1
				((ScriptID 240 21) setMotion: MoveTo 143 130 self)
			)
			(2
				((ScriptID 240 21) setMotion: MoveTo 40 130 self)
			)
			(3
				((ScriptID 240 21) setHeading: 0 self)
			)
			(4
				((ScriptID 240 22) setCycle: End self)
			)
			(5
				((ScriptID 240 21)
					setPri: 1
					setMotion: MoveTo 45 120 self
				)
			)
			(6
				((ScriptID 240 22) setCycle: Beg self)
			)
			(7
				(= global130 1)
				((ScriptID 240 21) dispose:)
				((ScriptID 240 22) stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance cliffyGoesBackToWD40 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 240 21) setMotion: MoveTo 164 145 self)
			)
			(1
				((ScriptID 240 21) setMotion: MoveTo 172 169 self)
			)
			(2
				(if (== wd40State 1)
					((ScriptID 240 21) setMotion: MoveTo 227 170 self)
				else
					((ScriptID 240 21) setMotion: MoveTo 239 164 self)
				)
			)
			(3
				(if (== wd40State 1)
					((ScriptID 240 21) setHeading: 0 self)
				else
					((ScriptID 240 21) setHeading: 90 self)
				)
			)
			(4
				(if (== wd40State 1)
					((ScriptID 240 21)
						view: 248
						loop: 0
						cel: 0
						x: 226
						y: 170
						setPri: 13
						setScale: Scaler 116 71 149 123
						setCycle: Fwd
						setScript: (ScriptID 240 23)
					)
					(= global130 2)
				else
					((ScriptID 240 21)
						view: 191
						loop: 0
						x: 239
						y: 164
						detailLevel: 2
						setPri: 13
						setScale: Scaler 116 71 149 123
						setScript: (ScriptID 240 23)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance funnyBeam of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 7
					loop: (= register (Random 0 2))
					cel: 0
					setSpeed: 6
					setScale: 0
					setCycle: End self
				)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(1
				(theMusic2 number: 102 loop: 1 play:)
				(if (== register 2)
					(extra2
						view: 7
						x: (- (ego x?) 7)
						y: (- (ego y?) 51)
						loop: 3
						setPri: (+ (ego priority?) 1)
						init:
						cycleSpeed: 12
						setCycle: ForwardCounter 3 self
					)
				else
					(= state (+ state 4))
					(= cycles 1)
				)
			)
			(2
				(extra2 loop: 4 setCycle: End self)
			)
			(3 (extra2 setCycle: Beg self))
			(4
				(extra2 loop: 5 setCycle: End self)
			)
			(5 (extra2 setCycle: Beg self))
			(6
				(if (cast contains: extra2)
					(extra2 loop: 3 setCycle: Fwd)
				)
				(= seconds 3)
			)
			(7
				(extra2 cycleSpeed: 6 dispose:)
				(ego setCycle: Beg self)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(8
				(ego
					view: 6
					loop: 0
					cel: 0
					setSpeed: 6
					setCycle: End self
				)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(9
				(theMusic2 stop:)
				(self dispose:)
			)
		)
	)
)

(instance extra1 of Actor
	(properties
		x 126
		y 164
		view 253
		priority 14
		signal $4010
	)
)

(instance extra2 of Prop
	(properties
		x 119
		y 146
		view 253
		loop 1
		signal $4000
	)
)

(instance extra3 of Prop
	(properties
		x 135
		y 174
		view 253
		loop 3
		priority 13
		signal $4010
	)
)

(instance beaArm of Actor
	(properties
		x 150
		y 128
		view 261
		loop 2
		priority 10
		signal $6810
	)
	
	(method (init)
		(super init: &rest)
		(self setMotion: MoveTo 116 108 upFromThrakus)
	)
)
