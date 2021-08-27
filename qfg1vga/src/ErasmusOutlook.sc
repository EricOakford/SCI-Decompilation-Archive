;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh) (include "28.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm28 0
)

(local
	greenSignShown
	redSignShown
	oldSortedFeatures
	climbingMountain
	theEyeIcon
	signReads
)
(instance rm28 of Room
	(properties
		noun N_ROOM
		picture 27
	)
	
	(method (init)
		(LoadMany VIEW 28 530)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						137 159
						0 154
						0 72
						136 136
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						210 176
						159 176
						140 170
						150 158
						209 137
						319 90
						319 189
						0 189
						0 186
						223 186
					yourself:
				)
		)
		(self style: (| BLACKOUT IRISOUT))
		(super init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(features
			add: rocks postPile road longShot
			eachElementDo: #init
		)
		;UPGRADE
;;;		(rocks init:)
;;;		(postPile init:)
;;;		(road init:)
;;;		(longShot init:)
		
		(cSound fade:)
		(StatusLine enable:)
		(= theEyeIcon (theIconBar at: ICON_LOOK))
		(theIconBar curIcon: theEyeIcon)
		(theGame setCursor: (theEyeIcon cursor?) TRUE)
		(if (== mountainSign 3)
			(= mountainSign 1)
		else
			(++ mountainSign)
		)
		(NormalEgo)
		(ego init:)
		(cond 
			((Btst fErasmusWarpOut)
				(Magic
					posn: 17 180
					setPri: (+ (ego priority?) 1)
					ignoreActors:
					init:
				)
				(ego setScript: teleport)
			)
			((== prevRoomNum 29)
				(ego setScript: downTheMount)
			)
			(else
				(ego setScript: egoEnters)
			)
		)
		(greenSign setPri: 13 posn: 69 172 init:)
		(redSign setPri: 13 posn: 147 173 init:)
	)
	
	(method (doit)
		(cond 
			((ego script?))
			((< (ego x?) 10)
				(ego setScript: egoExits)
			)
			((and (> (ego x?) 120) (< (ego y?) 170))
				(ego setScript: lookinUp)
			)
			(
				(and
					(== (ego loop?) 0)
					(== (ego onControl: origin) cBLUE)
					(not greenSignShown)
				)
				(= greenSignShown TRUE)
				(greenSign setScript: showGreenSign)
			)
			(
				(and
					(== (ego loop?) 0)
					(== (ego onControl: origin) cCYAN)
					(not redSignShown)
				)
				(= redSignShown TRUE)
				(redSign setScript: showRedSign)
			)
			(
				(and
					(== (ego loop?) 0)
					(== (ego onControl: origin) cRED)
					redSignShown
				)
				(= redSignShown FALSE)
			)
			(
				(and
					(== (ego loop?) 0)
					(== (ego onControl: origin) cGREEN)
					greenSignShown
				)
				(showGreenSign cue:)
			)
			((and (== (ego onControl: origin) cBLACK) greenSignShown)
				(= greenSignShown FALSE)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= useSortedFeatures oldSortedFeatures)
		(Bset fBeenIn28)
		(super dispose:)
	)
	
	(method (doVerb theVerb param2)
		(if (== theVerb V_LOOK)
			(if climbingMountain
				(messager say: N_ROOM V_LOOK C_CLIMBING_MOUNTAIN)
			else
				(messager say: N_ROOM V_LOOK C_AT_BASE)
			)
		else
			(super doVerb: theVerb param2 &rest)
		)
	)
	
	(method (cue)
		(switch signReads
			(5
				(messager say: N_GREENSIGN NULL NULL mountainSign)
			)
			(6
				(messager say: N_REDSIGN NULL NULL mountainSign)
			)
		)
	)
)

(instance rocks of Feature
	(properties
		noun N_ROCKS
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				147 159
				0 152
				0 64
				19 78
				18 91
				26 99
				33 100
				38 105
				45 105
				53 111
				61 116
				78 117
				90 127
				97 131
				103 130
				112 134
				129 132
				135 133
				143 131
				159 135
				162 123
				169 117
				185 115
				207 111
				220 96
				229 89
				237 84
				241 80
				258 69
				271 65
				284 67
				301 79
				319 84
				319 189
				0 189
				0 176
				13 175
				29 186
				45 187
				50 181
				88 187
				91 183
				109 182
				129 187
				133 183
				144 184
				148 186
				166 186
				177 188
				181 185
				201 185
				218 180
				235 186
				223 170
				162 173
				148 166
		)
		(super init:)
	)
)

(instance postPile of Feature
	(properties
		noun N_POSTPILE
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				82 120
				79 117
				61 115
				46 105
				38 105
				34 100
				26 98
				19 91
				20 78
				0 63
				0 30
				11 30
				33 41
				46 51
				54 40
				61 37
				80 37
				125 55
				151 51
				168 55
				183 31
				216 29
				204 48
				213 58
		)
		(super init:)
	)
)

(instance road of Feature
	(properties
		noun N_ROAD
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				159 135
				142 131
				133 133
				125 131
				112 134
				104 130
				96 130
				82 121
				213 58
				205 48
				217 29
				184 19
				203 0
				247 0
				222 17
				284 30
				267 41
				281 48
				267 65
				258 69
				240 80
				234 85
				222 94
				207 111
				169 116
				161 124
		)
		(super init:)
	)
)

(instance longShot of Feature
	(properties
		noun N_LONGSHOT
		nsBottom 140
		nsRight 319
	)
)

(instance magicMount of Feature
	(properties
		noun N_MAGICMOUNT
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				66 189
				116 180
				157 157
				185 135
				191 117
				203 103
				211 89
				251 89
				258 96
				260 100
				274 119
				283 119
				302 136
				319 142
				319 189
		)
		(super init:)
	)
)

(instance farPavilions of Feature
	(properties
		noun N_FARPAVILLIONS
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				127 173
				114 180
				63 189
				0 189
				0 177
				3 175
				9 179
				21 174
				34 179
				40 175
				45 177
				50 177
				54 180
				64 175
				67 177
				72 173
				78 175
				94 165
				98 166
				103 164
				111 170
				118 169
		)
		(super init:)
	)
)

(instance castle of Feature
	(properties
		noun N_CASTLE
	)
	
	(method (init)
		((= onMeCheck (Polygon new:))
			type: PTotalAccess
			init:
				249 88
				210 88
				207 94
				209 74
				212 69
				213 57
				216 57
				219 76
				224 70
				220 59
				224 49
				229 46
				229 39
				227 39
				227 36
				231 34
				229 30
				232 25
				236 30
				236 34
				239 36
				237 39
				238 44
				243 49
				243 55
				240 60
				241 67
				249 83
		)
		(super init:)
	)
	
	(method (doVerb theVerb param2)
		(if (== theVerb V_LOOK)
			(switch (Random 0 3)
				(0
					(messager say: N_CASTLE V_LOOK C_RAND2)
				)
				(1
					(messager say: N_CASTLE V_LOOK C_RAND4)
				)
				(2
					(messager say: N_CASTLE V_LOOK C_RAND3)
				)
				(3
					(messager say: N_CASTLE V_LOOK C_RAND1)
				)
			)
		else
			(super doVerb: theVerb param2 &rest)
		)
	)
)

(instance redSign of Prop
	(properties
		view 28
		loop 1
	)
)

(instance greenSign of Prop
	(properties
		view 28
	)
)

(instance Magic of Prop
	(properties
		view 530
		cycleSpeed 2
	)
)

(instance downTheMount of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 12
					posn: 178 169
					init:
					setMotion: MoveTo 107 163 self
				)
			)
			(1
				(ego setMotion: MoveTo 24 170 self)
			)
			(2
				(ego setMotion: PolyPath 24 174 self)
			)
			(3
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lookinUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 12 setMotion: PolyPath 178 169 self)
			)
			(1 (ego hide:) (= ticks 240))
			(2
				(curRoom drawPic: 28 SCROLLDOWN)
				(features eachElementDo: #dispose release:)
				(= ticks 6)
			)
			(3
				(HandsOn)
				(theIconBar disable:
					ICON_WALK
					ICON_DO
					ICON_TALK
					ICON_CAST
					ICON_ACTIONS
					ICON_USEIT
					ICON_INVENTORY
				)
				(features
					add: magicMount farPavilions castle
					eachElementDo: #init
				)
				;UPGRADE
;;;				(magicMount init:)
;;;				(farPavilions init:)
;;;				(castle init:)
				
				(= climbingMountain TRUE)
				(= ticks 260)
			)
			(4
				(= ticks 260)
			)
			(5
				(ego setPri: -1)
				(curRoom newRoom: 29)
			)
		)
	)
)

(instance egoExits of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1
				(curRoom newRoom: 27)
			)
		)
	)
)

(instance showGreenSign of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(signSound play:)
				(greenSign ignoreActors: FALSE setCycle: EndLoop self)
			)
			(1
				(signSound stop:)
				(= signReads 5)
				(if (not (Btst fSawGreenSign))
					(messager say: N_ROOM NULL C_GREEN_SIGN_APPEARS 1 curRoom)
				else
					(rm28 cue:)
				)
				(Bset fSawGreenSign)
				(greenSign ignoreActors: setCycle: BegLoop self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance showRedSign of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(signSound play:)
				(redSign ignoreActors: FALSE setCycle: EndLoop self)
			)
			(1
				(signSound stop:)
				(ego setMotion: 0)
				(= signReads 6)
				(if (not (Btst fSawRedSign))
					(messager say: N_ROOM NULL C_RED_SIGN_APPEARS 1 curRoom)
				else
					(rm28 cue:)
				)
				(Bset fSawRedSign)
				(redSign ignoreActors: setCycle: BegLoop self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance teleport of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 17 180 loop: 2 cel: 1 hide:)
				(Magic cycleSpeed: 4 setCycle: CycleTo 5 1 self)
			)
			(1
				(signSound play:)
				(ego show:)
				(Magic setCycle: CycleTo 3 -1 self)
			)
			(2
				(Magic setCycle: EndLoop self)
			)
			(3
				(signSound stop:)
				(Bclr fErasmusWarpOut)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance egoEnters of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(ego posn: 1 180 setMotion: MoveTo 20 180 self)
			)
			(1
				1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance signSound of Sound
	(properties
		number 28
	)
)
