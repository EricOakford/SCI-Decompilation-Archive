;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm77 0
)

(local
	local0
)
(procedure (localproc_18b4)
	(if (== (poofAway caller?) scared)
		((ScriptID 0 21) number: 27 loop: 1 play:)
	)
)

(instance rm77 of Room
	(properties
		picture 77
		east 76
		west 78
	)
	
	(method (init)
		(LoadMany VIEW 170 171 172 174 173 179 178)
		(LoadMany SOUND 66 48)
		(self style:
			(switch prevRoomNum
				(west WIPERIGHT)
				(east WIPELEFT)
			)
		)
		(super init:)
		(= local0 0)
		(switch prevRoomNum
			(east
				(if (not (Btst fVisitedLeprechaunThrone))
					(ego
						setScript:
							(cond 
								((Btst fLepsDance) danceFever)
								((ego has: iFourLeafClover) scared)
								(else danceFever)
							)
					)
				)
				(ego posn: 315 (proc0_17 187 (ego y?) 173))
			)
			(west
				(ego posn: 3 (proc0_17 187 (ego y?) 175))
			)
			(else
				(ego posn: 150 180)
			)
		)
		(ego init:)
		(NormalEgo)
		(if (not (ego has: iMagicShield))
			(shield init: stopUpd:)
		)
		(if (Btst fSceptreOnThrone)
			(sceptre init: stopUpd:)
		)
		(log1 init:)
		(log2 init:)
		(log3 init:)
		(log4 init:)
		(log5 init:)
		(log6 init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script (script doit:))
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (dispose)
		(Bset fVisitedLeprechaunThrone)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(
				(or
					(Said 'get,take,take,move,pull,lift/carpet,carpet')
					(Said 'look,check<below/carpet,carpet')
				)
				(Print 77 0)
			)
			((Said 'sit[<on,in,down][/throne]')
				(if (Btst fSceptreOnThrone)
					(Print 77 1)
				else
					(Print 77 2)
				)
			)
			((Said 'eat,consume/mushroom')
				(if (ego has: iMushroom)
					(Print 77 3)
				else
					(DontHave)
				)
			)
			(
				(or
					(Said 'look,check/carpet,carpet')
					(MouseClaimed event 152 112 186 161)
					(MouseClaimed event 186 131 194 161)
					(MouseClaimed event 143 131 151 161)
					(MouseClaimed event 193 148 199 161)
					(MouseClaimed event 137 149 142 161)
				)
				(Print 77 4)
			)
			(
				(or
					(Said 'look,check/throne')
					(MouseClaimed event 148 85 192 112)
				)
				(cond 
					((cast contains: king)
						(if (!= (king view?) 171)
							(Print 77 5)
						)
					)
					((Btst fSceptreOnThrone)
						(Print 77 6)
					)
					(else
						(Print 77 7)
					)
				)
			)
			((or (Said 'look,check/scepter') (MousedOn sceptre event shiftDown))
				(cond 
					((ego has: iSceptre)
						(event claimed: FALSE)
					)
					((not (Btst fSceptreOnThrone))
						(Print 77 8)
					)
					((ego inRect: 151 110 177 115)
						(Print 77 9)
					)
					(else
						(Print 77 10)
					)
				)
			)
			((or (Said 'look,check/shield') (MousedOn shield event shiftDown))
				(cond 
					((ego has: iMagicShield)
						(event claimed: FALSE)
					)
					((ego inRect: 180 110 210 119)
						(Print 77 11)
					)
					(else
						(Print 77 12)
					)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(Print 77 13)
						(cond 
							((and (>= (king signal?) 2) (<= (king signal?) 4))
								(cond 
									((ego has: iMagicShield)
										(Print 77 14)
									)
									((not (ego has: iMagicShield))
										(Print 77 15)
									)
								)
							)
							((and (Btst fSceptreOnThrone) (ego has: iMagicShield))
								(Print 77 16)
							)
							((and (Btst fSceptreOnThrone) (not (ego has: iMagicShield)))
								(Print 77 17)
							)
							((and (not (Btst fSceptreOnThrone)) (not (ego has: iMagicShield)))
								(Print 77 18)
							)
						)
					)
					((Said '/lep')
						(Print 77 19)
					)
					((Said '/king,man')
						(cond 
							((not (cast contains: king))
								(Print 77 20)
							)
							((== (king view?) 171)
								(Print 77 21)
							)
							(else
								(Print 77 22)
							)
						)
					)
				)
			)
			((or (Said 'talk,speak/lep') (Said 'hello') (Said 'say/hello'))
				(if (cast contains: king)
					(Print 77 23)
				else
					(Print 77 24)
				)
			)
			((or (Said 'talk,speak/king,man') (Said 'hello') (Said 'say/hello'))
				(if (not (cast contains: king))
					(Print 77 25)
				else
					(Print 77 26)
				)
			)
			((Said 'get,take/shield')
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((Btst fGotShield)
						(Print 77 27)
					)
					((not (ego inRect: 180 110 210 119))
						(CantReach)
					)
					(else
						(Print 77 28)
						((ScriptID 0 21) number: 66 play:)
						(ego loop: 3 setMotion: 0 get: 16)
						(SolvePuzzle fGotShield 8)
						(shield dispose:)
					)
				)
			)
			((Said 'get,take/scepter')
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((Btst fGotSceptre)
						(Print 77 27)
					)
					((not (Btst fSceptreOnThrone))
						(Print 77 29)
					)
					((not (ego inRect: 151 110 177 115))
						(CantReach)
					)
					(else
						((ScriptID 0 21) number: 105 loop: 1 init: play:)
						(Print 77 30)
						(ego loop: 3 setMotion: 0 get: 12)
						(Bclr fSceptreOnThrone)
						(SolvePuzzle fGotSceptre 6)
						(sceptre dispose:)
					)
				)
			)
			((Said 'kill,shoot,capture/king')
				(if (cast contains: king)
					(Print 77 31)
				else
					(event claimed: FALSE)
					(super handleEvent: event)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(& (OnControl PMAP (event x?) (event y?)) $a000)
				)
				(Print 77 32)
			)
			((Said '/stalactite,stalactite>')
				(cond 
					((Said 'take,bend')
						(Print 77 33)
					)
					((Said 'look,check')
						(Print 77 32)
					)
				)
			)
		)
	)
)

(instance danceFever of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (< local0 ((ScriptID 0 21) prevSignal?))
			(= local0 ((ScriptID 0 21) prevSignal?))
			((ScriptID 0 21) prevSignal: 0)
			(playPoofAway cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego stopUpd:)
				(king init: stopUpd:)
				(girl1 init: stopUpd:)
				(girl2 init: stopUpd:)
				(man1 init: stopUpd:)
				(man2 init: stopUpd:)
				(man3 init: stopUpd:)
				(drummer init: stopUpd:)
				(harpist init: stopUpd:)
				(man4 init: stopUpd:)
				(= cycles 1)
			)
			(1
				(Print 77 34)
				(if (>= howFast 1)
					(girl1 setCycle: Forward)
					(girl2 setCycle: Forward)
					(man1 setCycle: Forward)
					(man2 setCycle: Forward)
					(man3 setCycle: Forward)
					(king setCycle: Forward)
					(drummer setCycle: Forward)
					(harpist setCycle: Forward)
				)
				((ScriptID 0 21) number: 48 loop: 1 init: play:)
				(if (>= howFast 1)
					(man4 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if (>= howFast 1)
					(man4 loop: 0 cel: 0 setCycle: Forward)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(3
				(self setScript: playPoofAway self)
			)
			(4
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(poof dispose:)
				(Bset fSceptreOnThrone)
				(sceptre init:)
				(king
					setCycle: Forward
					setLoop: 2
					setMotion: MoveTo 178 120 self
				)
			)
			(5
				(king setMotion: MoveTo 152 160 self)
			)
			(6
				(king setMotion: MoveTo 174 176 self)
			)
			(7
				(HandsOn)
				(king setMotion: MoveTo 104 186 self)
			)
			(8
				(king setMotion: MoveTo -10 173 self)
			)
			(9
				(king dispose:)
				(self dispose:)
			)
		)
	)
)

(instance scared of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(king view: 170 loop: 0 cel: 0 init: stopUpd:)
				(girl1 loop: 2 cel: 0 init: stopUpd:)
				(girl2 loop: 2 cel: 1 init: stopUpd:)
				(man1 loop: 3 cel: 0 init: stopUpd:)
				(man2 loop: 3 cel: 1 init: stopUpd:)
				(man4 loop: 2 init: stopUpd:)
				(drummer loop: 1 init: stopUpd:)
				(harpist loop: 1 init: stopUpd:)
				(man3 init: loop: 2 cel: 0 cycleSpeed: 1 stopUpd:)
				(if (>= howFast 1)
					(man3 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(1
				(Print 77 35)
				(self setScript: poofAway self)
			)
			(2
				(HandsOff)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(poof
					cel: 0
					posn: (king x?) (king y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(king dispose:)
				(HandsOn)
				(poof setCycle: EndLoop self)
			)
			(4
				(poof dispose:)
				(self dispose:)
			)
		)
	)
)

(instance poofAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(poof
					init:
					cel: 0
					posn: (man1 x?) (man1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(1
				(man1 dispose:)
				(if (== (poofAway caller?) scared)
					((ScriptID 0 21) number: 27 init: loop: 1 play:)
				)
				(poof setCycle: EndLoop self)
			)
			(2
				(poof
					cel: 0
					posn: (man2 x?) (man2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(man2 dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(4
				(poof
					cel: 0
					posn: (man3 x?) (man3 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(5
				(man3 dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(6
				(poof
					cel: 0
					posn: (girl1 x?) (girl1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(7
				(girl1 dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(8
				(poof
					cel: 0
					posn: (girl2 x?) (girl2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(9
				(girl2 dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(10
				(poof
					cel: 0
					posn: (man4 x?) (man4 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(11
				(man4 dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(12
				(poof
					cel: 0
					posn: (drummer x?) (drummer y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(13
				(drummer dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(14
				(poof
					cel: 0
					posn: (harpist x?) (harpist y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(15
				(harpist dispose:)
				(localproc_18b4)
				(poof setCycle: EndLoop self)
			)
			(16 (HandsOn) (self dispose:))
		)
	)
)

(instance playPoofAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 0))
			(1
				(HandsOff)
				(poof
					init:
					cel: 0
					posn: (man1 x?) (man1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(2
				(man1 dispose:)
				(poof setCycle: EndLoop self)
			)
			(3 (= cycles 0))
			(4
				(poof
					cel: 0
					posn: (man2 x?) (man2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(5
				(man2 dispose:)
				(poof setCycle: EndLoop self)
			)
			(6 (= cycles 0))
			(7
				(poof
					cel: 0
					posn: (man3 x?) (man3 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(8
				(man3 dispose:)
				(poof setCycle: EndLoop self)
			)
			(9 (= cycles 0))
			(10
				(poof
					cel: 0
					posn: (girl1 x?) (girl1 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(11
				(girl1 dispose:)
				(poof setCycle: EndLoop self)
			)
			(12 (= cycles 0))
			(13
				(poof
					cel: 0
					posn: (girl2 x?) (girl2 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(14
				(girl2 dispose:)
				(poof setCycle: EndLoop self)
			)
			(15 (= cycles 0))
			(16
				(poof
					cel: 0
					posn: (man4 x?) (man4 y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(17
				(man4 dispose:)
				(poof setCycle: EndLoop self)
			)
			(18 (= cycles 0))
			(19
				(poof
					cel: 0
					posn: (drummer x?) (drummer y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(20
				(drummer dispose:)
				(poof setCycle: EndLoop self)
			)
			(21 (= cycles 0))
			(22
				(poof
					cel: 0
					posn: (harpist x?) (harpist y?)
					setCycle: CycleTo 7 1 self
				)
			)
			(23
				(harpist dispose:)
				(poof setCycle: EndLoop self)
			)
			(24 (HandsOn) (self dispose:))
		)
	)
)

(instance king of Actor
	(properties
		x 168
		y 97
		view 171
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 77 36)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shield of View
	(properties
		x 196
		y 108
		view 170
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 77 11)
			)
		)
	)
)

(instance sceptre of View
	(properties
		x 165
		y 88
		view 171
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 77 9)
			)
		)
	)
)

(instance girl1 of Prop
	(properties
		x 41
		y 88
		description {leprechaun}
		view 172
	)
)

(instance girl2 of Prop
	(properties
		x 250
		y 131
		description {leprechaun}
		view 172
		loop 1
	)
)

(instance man1 of Prop
	(properties
		x 25
		y 86
		description {leprechaun}
		view 174
	)
)

(instance man2 of Prop
	(properties
		x 137
		y 114
		description {leprechaun}
		view 174
		loop 1
	)
)

(instance man3 of Prop
	(properties
		x 167
		y 134
		description {leprechaun}
		view 174
		loop 2
	)
)

(instance man4 of Prop
	(properties
		x 116
		y 138
		description {leprechaun}
		view 173
		loop 1
	)
)

(instance drummer of Prop
	(properties
		x 211
		y 126
		view 179
	)
)

(instance harpist of Prop
	(properties
		x 231
		y 110
		view 178
	)
)

(instance poof of Prop
	(properties
		view 170
		loop 2
	)
	
	(method (doVerb)
	)
)

(instance log1 of NewFeature
	(properties
		x 59
		y 54
		noun '/timber,beam,branch,timber,beam'
		nsTop 24
		nsLeft 54
		nsBottom 85
		nsRight 64
		description {log}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)

(instance log2 of NewFeature
	(properties
		x 101
		y 78
		noun '/timber,beam,branch,timber,beam'
		nsTop 54
		nsLeft 97
		nsBottom 102
		nsRight 106
		description {log}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)

(instance log3 of NewFeature
	(properties
		x 135
		y 51
		noun '/timber,beam,branch,timber,beam'
		nsTop 21
		nsLeft 130
		nsBottom 82
		nsRight 140
		description {log}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)

(instance log4 of NewFeature
	(properties
		x 193
		y 47
		noun '/timber,beam,branch,timber,beam'
		nsTop 18
		nsLeft 189
		nsBottom 77
		nsRight 198
		description {log5}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)

(instance log5 of NewFeature
	(properties
		x 247
		y 68
		noun '/timber,beam,branch,timber,beam'
		nsTop 32
		nsLeft 241
		nsBottom 104
		nsRight 253
		description {log}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)

(instance log6 of NewFeature
	(properties
		x 299
		y 98
		noun '/timber,beam,branch,timber,beam'
		nsTop 80
		nsLeft 295
		nsBottom 116
		nsRight 303
		description {log}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The huge wooden columns help support the cave ceiling.}
	)
)
