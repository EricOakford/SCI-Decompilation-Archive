;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh) (include "30.shm")
(use Main)
(use Procs)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm30 0
)

(local
	goingUpstairs
	warned
	teleportCued
	local3
	forbiddenRoom
	leaveCued
	saveSortedFeatures
	dragonDunking
)
(instance rm30 of Room
	(properties
		noun N_ROOM
		picture 30
		style HSHUTTER
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						243 189
						243 186
						315 186
						315 125
						294 125
						294 92
						291 91
						291 105
						275 116
						253 111
						237 119
						205 115
						183 124
						153 126
						139 133
						150 142
						136 153
						125 150
						105 114
						195 59
						223 48
						166 8
						161 11
						186 40
						198 42
						205 50
						196 52
						103 105
						63 107
						86 140
						92 159
						73 162
						62 156
						44 138
						30 132
						25 106
						22 106
						24 128
						0 128
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						173 144
						250 125
						292 153
						209 175
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						147 134
						168 130
						184 138
						157 142
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						1 139
						21 138
						21 159
						71 175
						80 172
						94 170
						102 179
						117 181
						119 189
						0 189
					yourself:
				)
		)
		(LoadMany VIEW 530 30 199)
		(LoadMany SOUND 28)
		(super init:)
		(cSound number: 115 loop: -1 init: play:)
		(bobSound init:)
		(= saveSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(features
			add:
				rug
				armor
				ceiling
				stool
				foreGround
				vase
				bookShelf
				table
				theCasket
				westWall
				eastWall
				westStair
				eastStair
			eachElementDo: #init
		)
		;UPGRADE
;;;		(rug init:)
;;;		(armor init:)
;;;		(ceiling init:)
;;;		(stool init:)
;;;		(foreGround init:)
;;;		(vase init:)
;;;		(bookShelf init:)
;;;		(table init:)
;;;		(theCasket init:)
;;;		(westWall init:)
;;;		(eastWall init:)
;;;		(westStair init:)
;;;		(eastStair init:)
		
		(SolvePuzzle f30EnterTower 3)
		(StatusLine enable:)
		(NormalEgo)
		(ego posn: 160 189 init: setScript: intro)
		(peacock posn: 216 135 init: setScript: showFeathers)
		(dragon posn: 318 77 init: stopUpd:)
		(casket setPri: 15 posn: 49 169 init: stopUpd:)
		(dunker
			setPri: 11
			posn: 235 125
			init:
			setScript: swingBird
		)
		(theWiz setPri: 1 posn: 122 76 init: stopUpd:)
		(fenrus setPri: 15 posn: 263 171 init: stopUpd:)
		(lamp setPri: 8 posn: 157 77 init: setCycle: Forward)
	)
	
	(method (doit)
		(cond 
			((== (self script?) teleportOut))
			(teleportCued
				(HandsOff)
				(= teleportCued NULL)
				(self setScript: teleportOut)
			)
			((and (< (ego y?) 40) (not goingUpstairs))
				(HandsOff)
				(= goingUpstairs TRUE)
				(ego setScript: intoTheTower)
			)
			((and (== (ego edgeHit?) SOUTH) (not leaveCued))
				(HandsOff)
				(= leaveCued TRUE)
				(= teleportCued TRUE)
			)
			((and (== (ego onControl: origin) cBLUE) (not forbiddenRoom))
				(HandsOff)
				(ego setMotion: 0 setCycle: 0 setLoop: (ego loop?))
				(= forbiddenRoom TRUE)
				(= teleportCued TRUE)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= useSortedFeatures saveSortedFeatures)
		(Bset fBeenIn30)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_CALM
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_DAZZLE
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_DETECT
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_FETCH
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_OPEN
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_TRIGGER
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_ZAP
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(messager say: N_ERASMUS V_LOOK C_BEFORE_MET_ERASMUS 2)
	)
)

(instance Magic of Prop
	(properties
		view 530
		cycleSpeed 2
	)
)

(instance peacock of Prop
	(properties
		noun N_PEACOCK
		view 30
		loop 7
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(if (> (peacock cel?) 0)
					(messager say: N_PEACOCK V_LOOK C_FEATHERS_SHOWN)
					(showFeathers changeState: 3)
				else
					(messager say: N_PEACOCK V_LOOK C_FEATHERS_HIDDEN)
					(showFeathers changeState: 1)
				)
			)
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_TALK
				(messager say: N_PEACOCK V_ALTTALK NULL)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dragon of Prop
	(properties
		z 40
		noun N_DRAGON
		view 30
		loop 2
		cycleSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(self setScript: warning)
				)
			)
			(V_TALK
				(messager say: N_DRAGON V_ALTTALK)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance casket of Prop
	(properties
		noun N_CASKET
		view 30
		loop 6
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(self setCycle: EndLoop self)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(self cel: 0)
	)
)

(instance dunker of Prop
	(properties
		noun N_DUNKER
		view 30
		loop 4
		signal ignrAct
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if dragonDunking
					(= dragonDunking FALSE)
					(self setScript: swingBird)
				else
					(= dragonDunking TRUE)
					(messager say: N_DUNKER V_DO)
					(self setScript: 0 setLoop: 5 setCycle: EndLoop)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance theWiz of Prop
	(properties
		noun N_ERASMUS
		view 30
		loop 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(if (Btst fBeenIn31)
					(messager say: N_ERASMUS V_LOOK C_AFTER_MET_ERASMUS)
				else
					(messager say: N_ERASMUS V_LOOK C_BEFORE_MET_ERASMUS 1 curRoom)
				)
			)
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fenrus of Prop
	(properties
		noun N_FENRUS
		view 199
		loop 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lamp of Prop
	(properties
		noun N_LAMP
		view 30
		loop 1
		signal ignrAct
		cycleSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rug of Feature
	(properties
		noun N_RUG
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				119 189
				91 157
				131 148
				182 189
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance armor of Feature
	(properties
		noun N_ARMOR
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				89 104
				71 106
				63 71
				65 56
				73 40
				82 46
				89 23
				95 29
				89 49
				91 68
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ceiling of Feature
	(properties
		noun N_CEILING
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				319 25
				303 30
				296 20
				277 23
				269 18
				263 26
				252 20
				244 12
				231 4
				230 9
				224 8
				222 13
				214 12
				206 9
				198 9
				197 6
				190 2
				186 5
				186 11
				174 11
				170 6
				163 14
				155 17
				137 16
				134 12
				121 21
				89 15
				91 10
				86 9
				81 12
				74 12
				70 8
				56 24
				47 12
				41 15
				32 11
				30 19
				17 18
				16 32
				0 30
				0 0
				319 0
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb V_LOOK)
			(messager say: N_CEILING V_LOOK NULL)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance stool of Feature
	(properties
		noun N_STOOL
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				316 122
				310 125
				299 121
				303 116
				303 104
				308 101
				318 104
				314 115
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance foreGround of Feature
	(properties
		noun N_FOREGROUND
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				118 189
				0 189
				0 156
				4 153
				8 159
				9 172
				13 169
				22 174
				24 180
				26 176
				33 174
				38 178
				43 178
				46 179
				51 172
				60 178
				62 181
				71 185
				76 174
				84 179
				94 172
				98 180
				99 184
				102 183
				103 187
				114 183
		)
		(super init:)
	)
)

(instance bookShelf of Feature
	(properties
		noun N_BOOKSHELF
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				240 189
				242 169
				269 155
				304 149
				319 156
				319 189
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(messager say: N_BOOKSHELF V_LOOK NULL 0)
			)
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance vase of Feature
	(properties
		noun N_VASE
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				312 161
				302 164
				288 158
				287 152
				295 149
				296 145
				300 143
				305 144
				306 149
				313 155
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance table of Feature
	(properties
		noun N_TABLE
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				218 165
				212 167
				206 163
				206 149
				196 144
				197 152
				192 152
				187 149
				188 132
				186 131
				186 127
				248 114
				279 126
				279 131
				273 132
				273 151
				267 153
				262 151
				262 140
				218 150
		)
		(super init:)
	)
)

(instance theCasket of Feature
	(properties
		noun N_SARCOPHAGUS
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				24 140
				17 159
				9 164
				9 159
				5 153
				0 155
				0 125
				5 122
				15 124
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_SWORD
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_DAGGER
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_FLAME
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(V_ROCK
				(if warned
					(= teleportCued TRUE)
				else
					(dragon setScript: warning)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance westWall of Feature
	(properties
		noun N_WESTWALL
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				47 102
				29 120
				4 122
				0 124
				0 31
				5 27
				17 32
				22 17
				29 20
				46 15
				51 23
		)
		(super init:)
	)
)

(instance eastWall of Feature
	(properties
		noun N_EASTWALL
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				319 119
				270 97
				271 24
				286 22
				288 23
				296 21
				303 31
				316 24
				319 28
		)
		(super init:)
	)
)

(instance eastStair of Feature
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				144 141
				136 147
				125 143
				127 140
				122 135
				123 131
				114 116
				115 114
				119 112
				126 111
				128 116
				135 119
				134 123
				140 125
				143 132
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb V_LOOK)
			(foreGround doVerb: theVerb)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance westStair of Feature
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				84 156
				72 155
				43 131
				35 130
				29 121
				48 101
				60 103
				62 107
				86 140
		)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb V_LOOK)
			(foreGround doVerb: theVerb)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance swingBird of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					cycleSpeed: (Random 10 15)
					loop: 4
					setCycle: EndLoop self
				)
			)
			(1
				(bobSound play:)
				(= cycles (Random 10 25))
			)
			(2
				(client cel: 4 setCycle: BegLoop self)
			)
			(3
				(client cel: 0 loop: 5 setCycle: EndLoop self)
			)
			(4
				(self changeState: 0)
			)
		)
	)
)

(instance showFeathers of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (peacock stopUpd:))
			(1 (peacock setCycle: EndLoop self))
			(2 (peacock stopUpd:))
			(3 (peacock setCycle: BegLoop self))
			(4 (peacock stopUpd:))
		)
	)
)

(instance intro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 160 187 setMotion: MoveTo 160 184 self)
			)
			(1
				(if (not (Btst fBeenIn30))
					(curRoom doVerb: V_LOOK)
				)
				(ego setScript: 0)
			)
		)
	)
)

(instance intoTheTower of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 166 2 self)
			)
			(1 (= ticks 120))
			(2
				(wizSound init: play:)
				(theWiz cycleSpeed: 6 setCycle: EndLoop self)
			)
			(3 (= ticks 90))
			(4
				(theWiz hide: dispose:)
				(fenrus
					setLoop: 0
					setCel: 8
					cycleSpeed: 6
					setCycle: CycleTo 5 -1 self
				)
			)
			(5
				(wizSound play:)
				(fenrus setCycle: BegLoop self)
			)
			(6
				(fenrus hide: dispose:)
				(= ticks 60)
			)
			(7
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance warning of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(dragon startUpd: setCycle: Forward)
				(= ticks 20)
			)
			(1 (= ticks 100))
			(2
				(messager say: 12 0 4 1 self)
			)
			(3
				(= warned TRUE)
				(HandsOn)
				(dragon setCycle: 0 setCel: 0 stopUpd: setScript: 0)
			)
		)
	)
)

(instance teleportOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fenrus setLoop: 6 cycleSpeed: 4 setCycle: Forward)
				(dragon setCycle: Forward)
				(= ticks 60)
			)
			(1
				(cond 
					(forbiddenRoom
						(messager say: N_ROOM NULL C_WARP_OUT 1 self)
					)
					(leaveCued
						(messager say: N_ROOM NULL C_WARP_OUT 2 self)
					)
					(else
						(messager say: N_ROOM NULL C_WARP_OUT 3 self)
					)
				)
			)
			(2
				(Magic
					ignoreActors:
					cel: 0
					setPri: (ego priority?)
					posn: (ego x?) (ego y?)
					init:
					setCycle: CycleTo 5 1 self
				)
			)
			(3
				(ego hide:)
				(Magic setCycle: CycleTo 3 -1 self)
			)
			(4 (Magic setCycle: EndLoop self))
			(5
				(Bset fErasmusWarpOut)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(= ticks 18)
			)
			(6 (curRoom newRoom: 28))
		)
	)
)

(instance bobSound of Sound
	(properties
		number 116
	)
)

(instance wizSound of Sound
	(properties
		number 28
	)
)
