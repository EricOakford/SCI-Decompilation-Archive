;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use Follow)
(use RFeature)
(use Gauge)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	intro 0
)

(local
	local0
	[local1 6]
	[local7 6] = [263 224 62 24 203 123]
	[local13 6] = [167 171 169 159 178 178]
	[local19 6] = [3 3 3 3 2 1]
	local25
	[local26 3]
	[local29 3] = [51 170 288]
	[local32 3] = [155 156 158]
)
(procedure (cls)
	(if modelessDialog (modelessDialog dispose:))
)

(instance intro of Room
	(properties
		picture 83
		style $000a
	)
	
	(method (init)
		(LoadMany VIEW 190 186 185 201 657 5 2 0 80 81 267 202)
		(LoadMany PICTURE 83 1 53)
		(LoadMany SOUND 81 82 78)
		(Load SCRIPT 778)
		(super init:)
		(addToPics add: urn eachElementDo: #init doit:)
		(Load SCRIPT 778)
		(ego posn: 330 117 init: setScript: songScript)
		(if (>= howFast 1)
			(theMenace
				view: 80
				setPri: 1
				loop: (ego loop?)
				x: (ego x?)
				cel: (ego cel?)
				y: (+ (ego y?) 20)
				ignoreActors:
				init:
			)
		)
		(= local0 0)
		(while (< local0 6)
			((= [local1 local0] (Clone Prop))
				view: 202
				cycleSpeed: 1
				setPri: 3
				x: [local7 local0]
				y: [local13 local0]
				setLoop: [local19 local0]
				ignoreActors: 1
				ignoreActors: 1
				sightAngle: 180
				closeRangeDist: 500
				longRangeDist: 500
				description: {ripples}
				init:
				stopUpd:
			)
			(if (>= howFast 1) ([local1 local0] setCycle: Forward))
			(++ local0)
		)
		(self setScript: beginScript)
	)
	
	(method (dispose)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		((ScriptID 0 23) fade:)
		((ScriptID 0 21) fade:)
		(super dispose:)
	)
)

(instance songScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 23) number: 81 init: play: self)
			)
			(1
				((ScriptID 0 23) number: 82 play: self)
			)
			(2 (self dispose:))
		)
	)
)

(instance beginScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetCursor theCursor FALSE)
		(if
			(and
				(== (curRoom picture?) 83)
				(< (ego x?) 58)
				(>= howFast 1)
			)
			(theMenace dispose:)
		)
		(if (and (== state 7) (<= (ego x?) 5))
			(cls)
		)
		(if (>= howFast 1)
			(if (== (ego view?) 0)
				(theMenace
					view: 80
					loop: (ego loop?)
					x: (ego x?)
					cel: (ego cel?)
					y: (+ (ego y?) 20)
				)
			else
				(theMenace
					view: 81
					loop: (ego loop?)
					x: (ego x?)
					cel: 0
					y: (+ (ego y?) 20)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 20))
			(1
				(SetCursor theCursor FALSE)
				(ego
					view: 0
					illegalBits: 0
					ignoreActors:
					setCycle: StopWalk 2
					signal: 4
					setMotion: MoveTo 245 117 self
				)
			)
			(2 (ego loop: 2) (self cue:))
			(3
				(Print 200 0 #at 16 20 #time 10 #dispose)
				(= cycles 30)
			)
			(4
				(ego setMotion: MoveTo 195 117 self)
			)
			(5
				(cls)
				(Print 200 1 #at 32 125 #width 248 #dispose)
				(ego setMotion: MoveTo 88 117 self)
			)
			(6
				(ego setMotion: MoveTo 65 105 self)
			)
			(7
				(ego setMotion: MoveTo -10 105 self)
			)
			(8
				(cls)
				(curRoom picture: 1 style: SCROLLRIGHT drawPic: 1)
				(gate init: setPri: 3 stopUpd:)
				(addToPics
					add: guard2 urn1 urn2
					eachElementDo: #init
					doit:
				)
				(guard1 init: stopUpd:)
				(ego posn: 318 105 setMotion: MoveTo 245 105)
				(= local0 0)
				(while (< local0 6)
					([local1 local0] dispose:)
					(++ local0)
				)
				(= local25 0)
				(while (< local25 3)
					((= [local26 local25] (Clone Prop))
						view: 202
						cycleSpeed: 1
						setPri: 3
						x: [local29 local25]
						y: [local32 local25]
						setLoop: 0
						ignoreActors: 1
						sightAngle: 180
						closeRangeDist: 500
						longRangeDist: 500
						description: {ripples}
						init:
						stopUpd:
						shiftClick: 0
					)
					(if (>= howFast 1)
						([local26 local25] setCycle: Forward)
					)
					(++ local25)
				)
				(= cycles 2)
			)
			(9
				(guard1 setCycle: Walk setMotion: MoveTo 160 105 self)
			)
			(10
				(guard1 setMotion: MoveTo 225 105 self)
			)
			(11
				(Print 200 2 #at 16 20 #dispose)
				(guard1 loop: 4 cel: 2)
				(guardFace
					posn: (- (guard1 x?) 2) (- (guard1 y?) 38)
					setPri: 7
					setCycle: Forward
					init:
				)
				(= seconds 7)
			)
			(12
				(guardFace dispose:)
				(cls)
				(Print 200 3 #at 16 20 #time 4 #dispose)
				(gramHead setPri: 9 posn: 242 67 setCycle: Forward init:)
				(guard1 setMotion: MoveTo 160 100 self)
			)
			(13
				(cls)
				(gramHead hide:)
				(guard1 setMotion: MoveTo 160 95)
				(ego setMotion: MoveTo 160 100 self)
			)
			(14
				(guard1 stopUpd: loop: 4 cel: 3)
				(ego loop: 3)
				(Print 200 4 #at 16 20 #dispose)
				(= seconds 2)
			)
			(15
				((ScriptID 0 21) number: 78 play:)
				(guard1 stopUpd:)
				(ego stopUpd:)
				(gate
					illegalBits: 0
					ignoreActors:
					yStep:
					(switch howFast
						(0 4)
						(else  1)
					)
					setMotion: MoveTo 160 25 self
				)
			)
			(16
				(cls)
				((ScriptID 0 21) stop:)
				(gate stopUpd:)
				(ego ignoreHorizon: setMotion: Follow guard1 23)
				(guard1 setPri: 2 setMotion: MoveTo 160 70 self)
			)
			(17
				(guard1 setMotion: MoveTo 100 70)
				(ego setPri: 2 setMotion: MoveTo 160 70 self)
			)
			(18
				(ego setMotion: MoveTo 120 70 self)
			)
			(19
				(cls)
				(guard1 posn: 337 154 setMotion: MoveTo 280 166 self)
				(ego posn: 330 144 setMotion: MoveTo 207 137 self)
				(gate dispose:)
				(= local25 0)
				(while (< local25 3)
					([local26 local25] dispose:)
					(++ local25)
				)
				(curRoom picture: 53 style: 2 drawPic: 53)
				(king init: setPri: 5 stopUpd:)
			)
			(20
				(guard1 setLoop: 4 setCel: 1 stopUpd:)
			)
			(21
				(ego setMotion: MoveTo 173 123 self)
			)
			(22
				(ego setMotion: MoveTo 139 123 self)
			)
			(23
				(ego view: 5 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(24
				(cls)
				(Print 200 5 #at 16 20 #width 276 #dispose)
				(ego stopUpd:)
				(gramHead show: posn: 136 95 setCycle: Forward)
				(= seconds 5)
			)
			(25
				(gramHead hide:)
				(talkingHead init: setCycle: Forward cycleSpeed: 1)
				(cls)
				(Print 200 6 #at 16 20 #width 276 #dispose)
				(= seconds 10)
			)
			(26
				(cls)
				(Print 200 7 #at 16 20 #width 276 #dispose)
				(= seconds 6)
			)
			(27
				(cls)
				(Print 200 8 #at 16 20 #width 276 #dispose)
				(= seconds 4)
			)
			(28
				(cls)
				(Print 200 9 #at 16 20 #width 276 #dispose)
				(= seconds 8)
			)
			(29
				(cls)
				(Print 200 10 #at 16 20 #width 276 #dispose)
				(= seconds 7)
			)
			(30
				(cls)
				(Print 200 11 #at 16 20 #width 276 #dispose)
				(= seconds 6)
			)
			(31
				(cls)
				(Print 200 12 #at 16 20 #width 276 #dispose)
				(= seconds 6)
			)
			(32
				(cls)
				(Print 200 13 #at 16 20 #width 230 #dispose #icon 503 2 0)
				(= seconds 8)
			)
			(33
				(cls)
				(Print 200 14 #at 16 20 #width 230 #dispose #icon 522 0 2)
				(= seconds 8)
			)
			(34
				(cls)
				(Print 200 15 #at 16 20 #width 230 #dispose #icon 505 0 1)
				(= seconds 8)
			)
			(35
				(cls)
				(Print 200 16 #at 16 20 #width 276 #dispose)
				(= seconds 8)
			)
			(36
				(cls)
				(Print 200 17 #at 16 20 #width 276 #dispose)
				(= seconds 7)
			)
			(37
				(cls)
				(Print 200 18 #at 16 20 #width 276 #dispose)
				(= seconds 8)
			)
			(38
				(cls)
				(Print 200 19 #at 16 20 #width 276 #dispose)
				(= seconds 6)
			)
			(39
				(talkingHead setCel: 0 stopUpd:)
				(cls)
				(Print 200 20 #at 16 20 #width 276 #dispose)
				(gramHead show: setCycle: Forward)
				(= seconds 5)
			)
			(40
				(gramHead dispose:)
				(ego setCycle: BegLoop self)
			)
			(41
				(cls)
				(ego view: 0 setLoop: 1 setCycle: StopWalk 2)
				(= seconds 2)
			)
			(42
				(ego setLoop: -1 setMotion: MoveTo 173 123 self)
			)
			(43
				(ego setMotion: MoveTo 207 137 self)
			)
			(44
				(ego setMotion: MoveTo 255 136 self)
			)
			(45
				(guard1
					setLoop: -1
					startUpd:
					setCycle: Walk
					setMotion: MoveTo 340 154
				)
				(ego setMotion: MoveTo 330 144 self)
			)
			(46
				(cls)
				(HandsOn)
				(curRoom newRoom: 86)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(super handleEvent: event)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(ENTER
					(if
						(or
							(and (== (curRoom picture?) 83) (> (ego x?) 0))
							(and (== (curRoom picture?) 1) (> (ego x?) 123))
							(and (== (curRoom picture?) 53) (< (ego x?) 320))
						)
						(event claimed: TRUE)
						(curRoom newRoom: 86)
					else
						(event claimed: TRUE)
					)
				)
				(`#2
					(if (GetMenu soundI p_value)
						(DoSound SoundOn FALSE)
						(SetMenu soundI p_value FALSE p_text {Turn on})
					else
						(DoSound SoundOn TRUE)
						(SetMenu soundI p_value TRUE p_text {Turn off})
					)
				)
				(`^v
					(= i
						((Gauge new:)
							description:
								{Use the mouse or right and left arrow keys to set the sound volume.}
							text: {Sound Volume}
							minimum: 0
							normal: 12
							maximum: 15
							higher: {Louder}
							lower: {Softer}
							doit: (DoSound ChangeVolume)
						)
					)
					(DoSound ChangeVolume i)
					(DisposeScript GAUGE)
				)
				(`#7
					(SetCursor theCursor TRUE)
					(theGame restore:)
				)
				(else  (event claimed: TRUE))
			)
		)
	)
)

(instance gate of Actor
	(properties
		x 160
		y 80
		view 201
	)
)

(instance guard1 of Actor
	(properties
		x 136
		y 92
		description {guard}
		view 185
		loop 4
	)
)

(instance guard2 of RPicView
	(properties
		x 197
		y 92
		description {guard}
		view 186
		loop 4
	)
)

(instance king of Prop
	(properties
		x 80
		y 119
		view 190
		loop 2
	)
)

(instance talkingHead of Prop
	(properties
		x 66
		y 87
		view 190
		loop 1
	)
	
	(method (doVerb)
	)
)

(instance gramHead of Prop
	(properties
		view 5
		loop 2
		cycleSpeed 1
	)
	
	(method (doVerb)
	)
)

(instance urn of RPicView
	(properties
		x 34
		y 103
		description {urn}
		view 267
	)
)

(instance urn1 of RPicView
	(properties
		x 35
		y 103
		description {urn}
		view 267
	)
)

(instance urn2 of RPicView
	(properties
		x 292
		y 103
		description {urn}
		view 267
	)
)

(instance guardFace of Prop
	(properties
		view 185
		loop 5
		cycleSpeed 1
	)
)
