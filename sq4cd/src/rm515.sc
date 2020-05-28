;;; Sierra Script 1.0 - (do not remove this comment)
(script# 515)
(include game.sh)
(use Main)
(use brain)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Motion)
(use System)

(public
	rm515 0
)

(instance rm515 of SQRoom
	(properties
		picture 515
		style $000a
		north 500
		east 520
		west 514
		vanishingX 530
		vanishingY -450
	)
	
	(method (init &tmp temp0)
		(Load VIEW 507)
		(switch prevRoomNum
			(north
				(self
					setScript: enterScript 0 1
					vanishingX: 540
					vanishingY: -400
				)
			)
			(west
				(self setScript: enterScript 0 4)
			)
			(else  (HandsOn))
		)
		(ego init:)
		(self setRegions: BRAIN)
		(super init:)
		(if (== prevRoomNum 1)
			(brain level: 3)
			(ego setPri: -1 x: 300 y: 88)
			(= prevRoomNum 520)
		)
		(switch (brain level?)
			(1
				(brain
					makePolygon:
						0 0 319 0 319 131 117 131 115 142 117 153
						77 153 77 159 319 159 319 189 0 189
				)
			)
			(2
				(brain
					makePolygon: 0 189 0 176 180 176 215 130 319 130 319 188
				)
				(brain
					makePolygon: 0 153 0 0 319 0 319 110 197 110 153 153
				)
			)
			(3
				(brain
					makePolygon:
						0 0 149 0 95 48 96 57 93 63 57 63 57 70 83 70
						72 75 63 75 55 80 71 97 319 97 319 189 0 189
				)
				(brain makePolygon: 136 38 319 38 319 75 99 75)
				(brain makePolygon: 154 24 177 0 319 0 319 24)
			)
		)
		((ScriptID BRAIN 3)
			x: 137
			y: 123
			nsLeft: 132
			nsTop: 119
			nsBottom: 127
			nsRight: 142
			sightAngle: 90
			init:
		)
		((ScriptID BRAIN 4)
			x: 63
			y: 141
			nsLeft: 55
			nsTop: 135
			nsBottom: 147
			nsRight: 72
			sightAngle: 90
			init:
		)
		((ScriptID BRAIN 5)
			x: 170
			y: 61
			nsLeft: 164
			nsTop: 56
			nsBottom: 66
			nsRight: 177
			sightAngle: 90
			init:
		)
		(tube init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((ego script?))
			((IsObjectOnControl ego cRED) (ego setScript: (elevatorScript new:) 0 0))
			(
			(and (== (brain level?) 1) (IsObjectOnControl ego cMAGENTA)) (ego setScript: (elevatorScript new:) 0 1))
			((IsObjectOnControl ego cCYAN)
				(if (< (ego y?) 100)
					(brain exitDir: 45)
				else
					(brain exitDir: 270)
				)
			)
		)
		(if
			(and
				(not ((ScriptID BRAIN 1) script?))
				(not ((ScriptID BRAIN 2) script?))
			)
			(super doit:)
			(if (== ((ScriptID BRAIN 1) room?) 515)
				(cond 
					(((ScriptID BRAIN 1) script?))
					((IsObjectOnControl (ScriptID BRAIN 1) cRED) ((ScriptID BRAIN 1) setScript: (elevatorScript new:) 0 0))
					(
						(and
							(IsObjectOnControl (ScriptID BRAIN 1) cRED)
							(== ((ScriptID BRAIN 1) level?) 1)
						)
						((ScriptID BRAIN 1) setScript: (elevatorScript new:) 0 1)
					)
				)
			)
			(if (== ((ScriptID BRAIN 2) room?) 515)
				(cond 
					(((ScriptID BRAIN 2) script?))
					((IsObjectOnControl (ScriptID BRAIN 2) cRED) ((ScriptID BRAIN 2) setScript: (elevatorScript new:) 0 0))
					(
						(and
							(IsObjectOnControl (ScriptID BRAIN 2) 32)
							(== ((ScriptID BRAIN 2) level?) 1)
						)
						((ScriptID BRAIN 2) setScript: (elevatorScript new:) 0 1)
					)
				)
			)
			(if (== (brain level?) 2)
				(if (ego inRect: 60 150 110 180)
					(ego setPri: 5)
				else
					(ego setPri: 3)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 500 say: 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2] egoX egoY newX newY)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch register
					(1
						(= egoX 172)
						(= egoY -7)
						(= newX 131)
						(= newY 37)
					)
					(4
						(= egoX 5)
						(= egoY 170)
						(= newX 30)
						(= newY 170)
					)
				)
				(ego
					x: egoX
					y: egoY
					setMotion: PolyPath newX newY self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance elevatorScript of Script
	(properties)
	
	(method (dispose)
		(if (!= client ego)
			((client path?) value: (if register 75 else 209))
			(client
				z: 44
				setLoop: 0
				setCycle: Forward
				mover: (client path?)
			)
			((client body?) doit:)
			((client path?) moveDone:)
		)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (== client ego) (HandsOff))
				(client setPri: 2)
				(if register
					(client setMotion: MoveTo 79 166 self)
				else
					(client setMotion: MoveTo 63 67 self)
				)
			)
			(1 (client setHeading: 90 self))
			(2
				(globalSound number: 847 vol: 127 loop: 1 play:)
				(if (== client ego)
					(brain level: 0)
					(ego
						normal: 0
						view: 507
						setLoop: 0
						cycleSpeed: 12
						setCycle: EndLoop self
					)
				else
					((client body?) hide:)
					(client
						z: 0
						setLoop: 2
						setCel: 0
						cycleSpeed: 12
						setCycle: EndLoop self
					)
				)
			)
			(3
				(client hide:)
				(= cycles 10)
			)
			(4
				(globalSound number: 847 vol: 127 loop: 1 play:)
				(if (and (!= client ego) register) (client level: 3))
				(if register
					(client posn: 63 67)
				else
					(client posn: 79 166)
				)
				(if (== client ego) (= temp0 10) else (= temp0 6))
				(client show: setPri: 2 setCel: temp0 setCycle: BegLoop self)
			)
			(5
				(if (== client ego)
					((curRoom obstacles?) dispose:)
					(brain level: (if register 3 else 1))
					(switch (brain level?)
						(1
							(brain
								makePolygon:
									0 0 319 0 319 131 117 131 115 142 117 153
									77 153 77 159 319 159 319 189 0 189
							)
						)
						(3
							(brain
								makePolygon:
									0 0 149 0 95 48 96 57 93 63 57 63
									57 70 83 70 72 75 63 75 55 80 71 97
									319 97 319 189 0 189
							)
							(brain makePolygon: 136 38 319 38 319 75 99 75)
							(brain makePolygon: 154 24 177 0 319 0 319 24)
						)
					)
					(NormalEgo 0 3 510)
					(ego setPri: 2)
				else
					(client z: 44 setLoop: 0 setCycle: Forward)
				)
				(client
					setMotion: MoveTo (+ (client x?) 30) (client y?) self
				)
			)
			(6
				(if register (client setPri: -1))
				(if (== client ego)
					(curRoom
						vanishingX: (if register 540 else 530)
						vanishingY: (if register -400 else -450)
					)
					(HandsOn)
				else
					(switch (client level?)
						(1 (client setPri: 2))
						(2 (client setPri: 3))
						(3 (client setPri: -1))
					)
					((client body?) doit:)
				)
				(self dispose:)
			)
		)
	)
)

(instance tube of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb &rest)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (== (OnControl 1 (param1 x?) (param1 y?)) cYELLOW))
	)
)
