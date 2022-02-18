;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Intrface)
(use castle)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Path)
(use Motion)
(use Actor)
(use System)

(public
	rm065 0
)

(local
	local0
	[local1 106] = [0 154 12 154 10 160 10 176 24 177 13 186 298 186 293 181 93 181 101 171 145 171 156 175 314 175 309 170 254 169 239 153 288 153 280 146 187 145 182 131 165 129 155 120 120 119 119 114 76 115 76 122 88 123 64 145 45 133 39 122 71 98 71 93 46 85 45 70 63 70 70 73 270 73 270 67 72 67 66 63 11 63 12 68 35 69 39 86 67 95 34 118 37 131 60 150 0 149 0 0 319 0 319 189 0 189]
	[local107 10] = [127 126 160 126 164 131 162 137 121 137]
	[local117 7] = [50 139 39 129 37 120 -32768]
	[local124 5] = [39 114 52 107 -32768]
	[local129 3] = [68 99 -32768]
	[local132 3] = [64 91 -32768]
	[local135 9] = [50 89 39 84 37 75 39 69 -32768]
	[local144 9] = [40 76 39 84 50 89 64 91 -32768]
	[local153 5] = [68 99 52 107 -32768]
	[local158 3] = [39 114 -32768]
	[local161 9] = [37 120 39 129 50 139 65 147 -32768]
	[local170 9] = [1002 116 63 4 11 29 20 28 29]
)
(instance rm065 of KQ5Room
	(properties
		picture 65
		west 62
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 4 2 712)
		(poly1 points: @local1 size: 53)
		(poly2 points: @local107 size: 5)
		(self
			setRegions: 550
			setFeatures:
				stairs
				((Clone stairs)
					nsLeft: 29
					nsTop: 109
					nsRight: 63
					nsBottom: 149
					yourself:
				)
				bottle
				firePlace
				machine
				doorWay
				room
			addObstacle: poly1 poly2
		)
		(coals setCycle: RandCycle init:)
		(switch prevRoomNum
			(66
				(ego view: 4 loop: 1 setStep: 1 1 posn: 200 72)
			)
			(else 
				(curRoom setScript: enterLeft)
			)
		)
		(ego init:)
		(if
			(and
				(< (Random 1 100) 30)
				(not wizardTimer)
				(not henchmanTimer)
				(not (== prevRoomNum 66))
			)
			((ScriptID 550 7)
				init:
				illegalBits: 0
				ignoreActors: 1
				view: 702
				setScript: mordacksHere
				show:
			)
			(= local0 1)
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			((ego inRect: 0 148 12 154) (curRoom setScript: exitLeft))
			((& (= temp1 (ego onControl: 0)) $0002) (curRoom newRoom: 66))
			((& temp1 $0010) (CastleHandsOff) (self setScript: upStairScript))
			((& temp1 $0080) (CastleHandsOff) (self setScript: downStairScript))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
			(
				(and
					(== wizardState 3)
					(or (ego inRect: 75 142 191 171) (== local0 1))
				)
				(if (< (ego x?) 144)
					(= wizardX 185)
					(= wizardY 136)
					(= wizardAngle 225)
					(= global354 135)
				else
					(= wizardX 105)
					(= wizardY 124)
					(= wizardAngle 135)
					(= global354 315)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance lookBottleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 95 186 self)
			)
			(1
				(if (IsObject (ego head?)) ((ego head?) hide:))
				(ego
					normal: 0
					view: 712
					setLoop: 0
					cel: 0
					z: 6
					setPri: 15
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(SpeakAudio 673)
				(if (IsObject (ego head?)) ((ego head?) show:))
				(ego
					normal: 1
					view: 2
					setLoop: -1
					loop: 2
					z: 0
					setPri: -1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(CastleHandsOn)
				(= inCartoon 0)
				(self dispose:)
			)
		)
	)
)

(instance upStairScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 10 setMotion: upStair1 self)
			)
			(1
				(ego setPri: 9 setMotion: upStair2 self)
			)
			(2
				(ego view: 4 setStep: 1 1 setMotion: upStair3 self)
			)
			(3
				(ego setPri: 10 setLoop: 2 setMotion: upStair4 self)
			)
			(4
				(ego setLoop: -1 setMotion: upStair5 self)
			)
			(5
				(ego setPri: -1)
				(CastleHandsOn)
				(self dispose:)
			)
		)
	)
)

(instance downStairScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 10 setMotion: downStair1 self)
			)
			(1
				(ego setPri: 9 setLoop: 3 setMotion: downStair2 self)
			)
			(2
				(ego
					setLoop: -1
					view: 2
					setStep: 2 2
					setMotion: downStair3 self
				)
			)
			(3
				(ego setPri: 10 setMotion: downStair4 self)
			)
			(4
				(ego setPri: -1)
				(CastleHandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					view: 2
					setStep: 2 2
					posn: -7 152
					setMotion: MoveTo 23 152 self
				)
			)
			(1
				(if (not local0)
					(theMusic number: 891 loop: -1 playBed:)
					(CastleHandsOn)
				else
					(ego normal: 0)
					(mordacksHere cue:)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance exitLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(theMusic fade:)
				(ego setMotion: MoveTo -7 152 self)
			)
			(1 (curRoom newRoom: 62))
		)
	)
)

(instance upStair1 of Path
	(properties)
	
	(method (at param1)
		(return [local117 param1])
	)
)

(instance upStair2 of Path
	(properties)
	
	(method (at param1)
		(return [local124 param1])
	)
)

(instance upStair3 of Path
	(properties)
	
	(method (at param1)
		(return [local129 param1])
	)
)

(instance upStair4 of Path
	(properties)
	
	(method (at param1)
		(return [local132 param1])
	)
)

(instance upStair5 of Path
	(properties)
	
	(method (at param1)
		(return [local135 param1])
	)
)

(instance downStair1 of Path
	(properties)
	
	(method (at param1)
		(return [local144 param1])
	)
)

(instance downStair2 of Path
	(properties)
	
	(method (at param1)
		(return [local153 param1])
	)
)

(instance downStair3 of Path
	(properties)
	
	(method (at param1)
		(return [local158 param1])
	)
)

(instance downStair4 of Path
	(properties)
	
	(method (at param1)
		(return [local161 param1])
	)
)

(instance coals of Prop
	(properties
		x 281
		y 152
		view 712
		loop 1
		priority 12
		signal $4010
		cycleSpeed 2
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance stairs of RFeature
	(properties
		nsTop 72
		nsLeft 29
		nsBottom 108
		nsRight 80
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 667)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay of RFeature
	(properties
		nsTop 91
		nsLeft 5
		nsBottom 155
		nsRight 20
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 668)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance firePlace of RFeature
	(properties
		nsTop 138
		nsLeft 253
		nsBottom 165
		nsRight 314
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 669)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 674)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bottle of RFeature
	(properties
		nsTop 159
		nsLeft 71
		nsBottom 186
		nsRight 93
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 670)
					(event claimed: 1)
				)
				(verbDo
					(if (< (ego y?) 100)
						(SpeakAudio 675)
						(event claimed: 1)
					else
						(CastleHandsOff)
						(= inCartoon 1)
						(curRoom setScript: lookBottleScript)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance machine of RFeature
	(properties
		nsLeft 225
		nsBottom 59
		nsRight 261
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 671)
					(event claimed: 1)
				)
				(verbDo
					(if (< (ego y?) 100)
						(ego setMotion: PolyPath 214 69)
					else
						(SpeakAudio 676)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 672)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance mordacksHere of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theMusic number: 835 loop: 1 playBed:)
				(switch (Random 0 2)
					(0
						((ScriptID 550 7) setLoop: 11 posn: 294 177 cel: 0)
						((ScriptID 550 1) init: posn: 278 149 hide:)
					)
					(1
						((ScriptID 550 7) setLoop: 9 posn: 242 147 cel: 0)
						((ScriptID 550 1) init: posn: 226 118 hide:)
					)
					(2
						((ScriptID 550 7) setLoop: 9 posn: 185 140 cel: 0)
						((ScriptID 550 1) init: posn: 169 111 hide:)
					)
				)
			)
			(1
				(proc762_1 @local170 926 self)
			)
			(2
				((ScriptID 550 7) cycleSpeed: 1 setCycle: CycleTo 6 1 self)
			)
			(3
				((ScriptID 550 1) show: setCycle: Forward setPri: 15)
				(= seconds 2)
			)
			(4
				((ego head?) hide:)
				(ego
					view: 127
					setLoop: 0
					setCycle: EndLoop self
					cycleSpeed: 3
				)
			)
			(5
				(theAudio number: 7058 loop: 1 play:)
				(ego setLoop: 2 setCycle: Forward cycleSpeed: 3)
				(= seconds 4)
			)
			(6
				((ScriptID 550 1) hide:)
				((ScriptID 550 7) setCel: 255)
				(ego setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(theAudio stop:)
				((ScriptID 550 7) setCycle: BegLoop)
				(= seconds 3)
				(= inCartoon 0)
			)
			(8
				(= deathMessage 657)
				(EgoDead 73 0 -1)
			)
		)
	)
)
