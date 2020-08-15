;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh) (include "390.shm")
(use Main)
(use SQRoom)
(use EgoDead)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	local0
	local1
	local2 =  1
)
(instance rm390 of SQRoom
	(properties
		noun N_ROOM
		picture 390
	)
	
	(method (init)
		(if (!= prevRoomNum 460)
			(sound1 number: 331 flags: 1 loop: -1 play: setVol: 127)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init: 0 89 0 139 84 138 127 125 164
						126 198 134 201 138 256 137 291
						118 274 112 256 117 241 111 226
						114 216 109 214 101 180 89 241
						70 219 64 207 67 182 56 151 62
						127 53 160 46 137 38 88 45 118
						68 103 79 84 74 67 77 75 93 39
						98 28 82 23 80 0 85
					yourself:
				)
		)
		(super init:)
		(switch prevRoomNum
			(460
				(curRoom setScript: sEgoGoes)
			)
			(else 
				(curRoom setScript: sEgoComes)
			)
		)
		(closet init:)
		(armyKnife init:)
		(autoBuckCard init:)
		(bed init:)
		(blinds init:)
		(chair1 init:)
		(chair2 init:)
		(book init:)
		(boots init:)
		(buckazoid init:)
		(cigarButt init:)
		(if (not (Btst 3)) (clapMaster init: approachVerbs: 4))
		(clothes init:)
		(comPost init: approachVerbs: 4)
		(dresser init:)
		(gadget init:)
		(headBoard init:)
		(heater init:)
		(hintBook init:)
		(greenLamp init:)
		(kitchen init:)
		(matches init:)
		(mirror init: approachVerbs: 1)
		(mop init:)
		(pants init:)
		(wallPicture init:)
		(pillows init:)
		(plant init:)
		(pocketPal init:)
		(poster init:)
		(remote init:)
		(sierraLetter init:)
		(replicatorFeature init: approachVerbs: 4)
		(rocket init:)
		(socks init:)
		(table init:)
		(trashCan init:)
		(whistle init:)
		(outletR init:)
		(outletL init:)
		(closetControl init:)
		(plant2 init:)
		(comPostPad init:)
		(if (and (not (Btst fGotPin)) (Btst fGotClapmaster))
			(thePin init: approachVerbs: V_DO V_TAPEDPLIERS V_PLIERS)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(local2 0)
			((< (= local0 (ego distanceTo: greenLamp)) 50) (PalVary PalVaryStart 390 0 (- 100 (* local0 2))))
			(
				(and
					(< 55 (ego y?))
					(< (ego y?) 65)
					(< (ego x?) 160)
				)
				(= local1 1)
				(Palette
					PalIntensity 72 79
					(- 100 (* (- (ego y?) 65) 5))
				)
			)
			((and (>= (ego y?) 65) local1) (= local1 0) (Palette PalIntensity 72 79 100))
		)
	)
	
	(method (dispose)
		(if (!= newRoomNum 460) (sound1 fade:))
		(super dispose:)
	)
)

(instance sEgoComes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(sound2 number: 941 loop: 1 play:)
				(ego
					view: 364
					setCel: 0
					setLoop: 0
					init:
					posn: 82 82
					setSpeed: 9
					setCycle: EndLoop self
				)
			)
			(2
				(ego normalize: 900 setLoop: 2)
				(= local2 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoGoes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local2 1)
				(ego normalize: 900 setLoop: 3 init:)
				(= cycles 1)
			)
			(1
				(if
				(or (== selectedRoom curRoomNum) (== selectedRoom 0))
					(= local2 0)
					(theGame handsOn:)
					(self dispose:)
				else
					(ego setHeading: 180)
					(= ticks 60)
				)
			)
			(2
				(sound2 number: 926 loop: 1 play:)
				(ego
					view: 3630
					setCel: 0
					setLoop: 0
					setSpeed: 9
					posn: 82 82
					setCycle: EndLoop self
				)
			)
			(3
				(curRoom newRoom: selectedRoom)
				(self dispose:)
			)
		)
	)
)

(instance sEgoBends of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 32
					setCel: 0
					setLoop: 0
					setSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2
				(if register
					(client setScript: sEgoGetsFried)
				else
					(= cycles 1)
				)
			)
			(3
				(thePin dispose:)
				(ego get: 5 setCycle: BegLoop self)
			)
			(4
				(ego normalize: 900 setLoop: 3)
				(outletR case: 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoGetsTheClap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 31
					setCel: 0
					setLoop: 0
					setSpeed: 6
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(clapMaster dispose:)
				(thePin init: approachVerbs: 4 25 24)
				(Bset fGotClapmaster)
				(ego setCel: 5 setCycle: EndLoop self)
				(outletR case: 4)
			)
			(3
				(ego get: iBrokenClapmaster normalize: 900 setLoop: 3)
				(= cycles 2)
			)
			(4
				(messager say: N_CLAPMASTER V_DO 0 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoDoesPin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 33
					setCel: 0
					setLoop: 0
					setSpeed: 6
					setCycle: EndLoop self
				)
			)
			(2
				(client setScript: sEgoGetsFried)
			)
		)
	)
)

(instance sEgoGetsFried of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= scratch 10)
				(sound2 number: 922 loop: 1 flags: 1 play:)
				(ego view: 32 setCel: 0 setLoop: 1 setCycle: Forward)
				(= cycles 1)
			)
			(1
				(Palette PalIntensity 70 255 (RandTime 0 100))
				(ShakeScreen 1 1)
				(-- scratch)
				(if scratch (-- state))
				(= cycles 1)
			)
			(2
				(sound2 stop:)
				(Palette PalIntensity 70 255 100)
				(ego setCel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(3 (= seconds 3))
			(4 (EgoDead 1 self))
			;below is what happens if you select "Try Again"
			(5
				(ego normalize: setLoop: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance thePin of View
	(properties
		noun N_PIN
		approachX 153
		approachY 64
		x 173
		y 40
		priority 60
		fixPriority 1
		view 30
		loop 1
		signal (| ignrAct fixedLoop canUpdate) ;$4801
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 172 38 178 38 178 44 172 44
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(curRoom setScript: sEgoDoesPin)
					(return TRUE)
				)
				(V_PLIERS
					(curRoom setScript: sEgoBends 0 1)
					(return TRUE)
				)
				(V_TAPEDPLIERS
					(Bset fGotPin)
					(curRoom setScript: sEgoBends 0 0)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance clapMaster of View
	(properties
		noun N_CLAPMASTER
		approachX 186
		approachY 62
		x 201
		y 20
		priority 55
		fixPriority 1
		view 30
		cel 1
		signal (| ignrAct fixedLoop canUpdate) ;$4801
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(Bset fGotClapmaster)
					(curRoom setScript: sEgoGetsTheClap)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance closet of Feature
	(properties
		noun N_CLOSET
		sightAngle 20
		x 1
		y 53
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 0 4 25 24 26 79 0 84
					yourself:
				)
		)
		(super init:)
	)
)

(instance armyKnife of Feature
	(properties
		noun N_ARMYKNIFE
		sightAngle 20
		x 230
		y 40
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 232 39 227 39 226 41 229 42
					yourself:
				)
		)
		(super init:)
	)
)

(instance autoBuckCard of Feature
	(properties
		noun N_AUTOBUCK_CARD
		sightAngle 20
		x 118
		y 119
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 114 119 117 120 123 118 120 117
					yourself:
				)
		)
		(super init:)
	)
)

(instance bed of Feature
	(properties
		noun N_BED
		sightAngle 20
		x 264
		y 65
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 265 42 256 47 253 50 242 55
						238 55 232 59 224 60 211 66 209
						65 198 70 191 87 200 93 209 92
						215 97 225 98 240 102 247 107 253
						108 260 112 276 103 319 84 319
						51 296 50 270 43
					yourself:
				)
		)
		(super init:)
	)
)

(instance blinds of Feature
	(properties
		noun N_BLINDS
		sightAngle 20
		x 259
		y 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 234 19 240 4 244 0 272 0 282 18 283 26 269 23 261 27 234 20
					yourself:
				)
		)
		(super init:)
	)
)

(instance chair1 of Feature
	(properties
		noun N_CHAIR
		sightAngle 20
		x 99
		y 129
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 90 119 86 103 103 99 106 102 107 105 110 114 93 119
					yourself:
				)
		)
		(super init:)
	)
)

(instance chair2 of Feature
	(properties
		noun N_CHAIR
		sightAngle 20
		x 177
		y 129
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 177 114 180 107 178 105 182 100
						186 100 202 105 199 110 197 111 197
						121
					yourself:
				)
		)
		(super init:)
	)
)

(instance book of Feature
	(properties
		noun N_BOOK
		sightAngle 20
		x 291
		y 109
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 289 106 285 108 291 112 299 110
					yourself:
				)
		)
		(super init:)
	)
)

(instance boots of Feature
	(properties
		noun N_BOOTS
		sightAngle 20
		x 231
		y 107
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 227 96 227 103 225 106 232 111 242 108 239 104 233 102 233 95 226 94
					yourself:
				)
		)
		(super init:)
	)
)

(instance buckazoid of Feature
	(properties
		noun N_BUCKAZOID
		sightAngle 20
		x 194
		y 125
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 194 125 193 125 195 128 197 125
					yourself:
				)
		)
		(super init:)
	)
)

(instance cigarButt of Feature
	(properties
		noun N_CIGARBUTT
		sightAngle 20
		x 183
		y 124
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 186 122 178 124 179 125 187 124
					yourself:
				)
		)
		(super init:)
	)
)

(instance clothes of Feature
	(properties
		noun N_CLOTHES
		sightAngle 20
		x 300
		y 128
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 263 138 271 135 262 132 274 128
						279 131 293 122 302 117 319 119 319 139
					yourself:
				)
		)
		(super init:)
	)
)

(instance comPost of Feature
	(properties
		noun N_COMPOST
		sightAngle 20
		approachX 82
		approachY 82
		x 72
		y 38
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 66 35 65 42 77 40 77 33
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom newRoom: 460))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dresser of Feature
	(properties
		noun N_DRESSER
		sightAngle 20
		x 203
		y 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 188 30 184 52 209 62 223 58 223 40 234 37 233 32 205 25
					yourself:
				)
		)
		(super init:)
	)
)

(instance gadget of Feature
	(properties
		noun N_GADGET
		sightAngle 20
		x 133
		y 113
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 127 114 129 117 139 118 138 113
					yourself:
				)
		)
		(super init:)
	)
)

(instance headBoard of Feature
	(properties
		noun N_HEADBOARD
		sightAngle 20
		x 294
		y 45
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 269 23 261 28 264 33 264 42 296 50 319 52 319 36
					yourself:
				)
		)
		(super init:)
	)
)

(instance heater of Feature
	(properties
		noun N_HEATER
		sightAngle 20
		x 98
		y 58
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 91 41 89 73 96 76 107 74 102 70 103 42 97 40
					yourself:
				)
		)
		(super init:)
	)
)

(instance hintBook of Feature
	(properties
		noun N_HINTBOOK
		sightAngle 20
		x 112
		y 122
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 105 122 112 127 119 125 113 119
					yourself:
				)
		)
		(super init:)
	)
)

(instance kitchen of Feature
	(properties
		noun N_KITCHEN
		sightAngle 20
		x 126
		y 50
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 108 15 137 12 155 16 149 24 150 56 108 62
					yourself:
				)
		)
		(super init:)
	)
)

(instance greenLamp of Feature
	(properties
		noun N_GREENLAMP
		sightAngle 20
		x 242
		y 65
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 247 19 252 20 254 25 250 29 247 31 241 28 241 22 244 19
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(messager
						say:
							noun
							theVerb
							(cond 
								((< local0 12) 3)
								((and (<= 13 local0) (<= local0 45)) 2)
								(else 1)
							)
							0
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance matches of Feature
	(properties
		noun N_MATCHES
		sightAngle 20
		x 189
		y 126
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 185 125 187 127 191 126 189 124
					yourself:
				)
		)
		(super init:)
	)
)

(instance mirror of Feature
	(properties
		noun N_MIRROR
		sightAngle 20
		approachX 180
		approachY 60
		x 215
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 208 0 208 15 211 20 217 22 223 19 223 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance mop of Feature
	(properties
		noun N_MOP
		sightAngle 20
		x 174
		y 28
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 195 0 172 27 175 38 178 33 177 24
					yourself:
				)
		)
		(super init:)
	)
)

(instance pants of Feature
	(properties
		noun N_PANTS
		sightAngle 20
		x 245
		y 77
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 248 71 224 77 222 81 228 84 229 79 246
						75 235 81 234 89 240 90 242 82 262 77
					yourself:
				)
		)
		(super init:)
	)
)

(instance wallPicture of Feature
	(properties
		noun N_WALLPICTURE
		sightAngle 20
		x 315
		y 22
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 304 0 306 21 319 24 319 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance pillows of Feature
	(properties
		noun N_PILLOWS
		sightAngle 20
		x 287
		y 52
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 264 41 252 50 282 59 315 65 319 62 319 51 292 50
					yourself:
				)
		)
		(super init:)
	)
)

(instance plant of Feature
	(properties
		noun 27
		sightAngle 20
		x 51
		y 82
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 41 72 48 70 62 72 64 80 68
						85 57 89 58 90 41 94 44 87 40
						85 34 83 41 81
					yourself:
				)
		)
		(super init:)
	)
)

(instance pocketPal of Feature
	(properties
		noun N_POCKETPAL
		sightAngle 20
		x 166
		y 117
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 164 112 158 115 155 115 152 117 153 121 170 126 183 118
					yourself:
				)
		)
		(super init:)
	)
)

(instance poster of Feature
	(properties
		noun N_POSTER
		sightAngle 20
		x 68
		y 6
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 53 0 55 20 81 16 83 0
					yourself:
				)
		)
		(super init:)
	)
)

(instance remote of Feature
	(properties
		noun N_REMOTE
		sightAngle 20
		x 148
		y 127
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 149 121 144 128 144 130 147 130 154 125
					yourself:
				)
		)
		(super init:)
	)
)

(instance sierraLetter of Feature
	(properties
		noun N_SIERRA_LETTER
		sightAngle 20
		x 191
		y 132
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 183 129 187 132 184 132 185 136 200 136 198 131 200 131 194 127
					yourself:
				)
		)
		(super init:)
	)
)

(instance replicatorFeature of Feature
	(properties
		noun N_REPLICATOR
		sightAngle 20
		approachX 145
		approachY 41
		x 156
		y 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 144 0 144 7 139 12 156 17 166 4 165 0
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setInset: (ScriptID 800 0) 0 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rocket of Feature
	(properties
		noun N_ROCKET
		sightAngle 20
		x 195
		y 23
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 195 10 194 16 195 30 198 29 199 15
					yourself:
				)
		)
		(super init:)
	)
)

(instance socks of Feature
	(properties
		noun N_SOCKS
		sightAngle 20
		x 221
		y 103
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 224 97 213 98 221 102 211 105 214 107 225 104
					yourself:
				)
		)
		(super init:)
	)
)

(instance table of Feature
	(properties
		noun N_TABLE
		sightAngle 20
		x 143
		y 99
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 69 139 76 125 122 111 161 112 209 125 215 138
					yourself:
				)
		)
		(super init:)
	)
)

(instance trashCan of Feature
	(properties
		noun N_TRASHCAN
		sightAngle 20
		x 146
		y 49
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 143 43 143 53 148 55 148 42 143 42
					yourself:
				)
		)
		(super init:)
	)
)

(instance whistle of Feature
	(properties
		noun N_WHISTLE
		sightAngle 20
		x 117
		y 134
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 120 133 113 135 112 135 119 135
					yourself:
				)
		)
		(super init:)
	)
)

(instance outletR of Feature
	(properties
		noun N_OUTLET_R
		sightAngle 40
		x 175
		y 40
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 173 40 173 50 177 50 178 39
					yourself:
				)
		)
		(super init:)
	)
)

(instance outletL of Feature
	(properties
		noun N_OUTLET_L
		sightAngle 40
		x 83
		y 60
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 82 55 83 68 86 67 86 54
					yourself:
				)
		)
		(super init:)
	)
)

(instance closetControl of Feature
	(properties
		noun N_CLOSET_CONTROL
		sightAngle 40
		x 38
		y 40
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 35 42 35 50 42 49 42 40
					yourself:
				)
		)
		(super init:)
	)
)

(instance plant2 of Feature
	(properties
		noun N_PLANT2
		sightAngle 40
		x 6
		y 140
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 0 97 7 105 5 112 14 116 16 121 21
						123 23 135 16 124 13 133 14 139 0 139
					yourself:
				)
		)
		(super init:)
	)
)

(instance comPostPad of Feature
	(properties
		noun N_COMPOST_PAD
		sightAngle 40
		x 80
		y 80
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 75 76 84 75 92 80 88 85 78 86 70 84 68 80
					yourself:
				)
		)
		(super init:)
	)
)
