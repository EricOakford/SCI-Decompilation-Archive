;;; Sierra Script 1.0 - (do not remove this comment)
(script# FOREST) ;804
(include game.sh) (include "804.shm")
(use Main)
(use ThrowKnife)
(use ThrowRock)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Game)
(use System)

(public
	forestRegion 0
	walkIn 1
)

(local
	atClearing
	initEgoX
	initEgoY
	toX
	toY
	floorPoly
	northRoom
	southRoom
	eastRoom
	westRoom
	nextRoom
	walkingIn
	walkInDone
	randomJoke
)
(instance forestRegion of Region
	(properties
		modNum FOREST
		noun N_FOREST
	)
	
	(method (init &tmp pic sound)
		(super init: &rest)
		(Load VIEW 510)
		(LoadMany SCRIPT GETROCK)
		(if
			(and
				(<= 704 (= pic (curRoom picture?)))
				(<= pic 707)
			)
			(= atClearing TRUE)
		)
		(= sound (if Night 32 else 25))
		(if
			(or
				(== (cSound prevSignal?) -1)
				(!= (cSound number?) sound)
			)
			(cSound stop: number: sound loop: -1 priority: 0 play:)
		)
		(= initEgoX 0)
		(= initEgoY 0)
		(switch (ego edgeHit?)
			(NORTH
				(= initEgoX 160)
				(= initEgoY 230)
			)
			(SOUTH
				(= initEgoX 145)
				(= initEgoY 56)
			)
			(EAST
				(= initEgoX -30)
				(= initEgoY 110)
			)
			(WEST
				(= initEgoX 350)
				(= initEgoY 110)
			)
		)
		(cond 
			((== curRoomNum 11)
				(= northRoom 11)
				(= southRoom 17)
				(= eastRoom 12)
				(= westRoom 11)
			)
			((== curRoomNum 12)
				(= northRoom 10)
				(= southRoom 12)
				(= eastRoom 13)
				(= westRoom 11)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 103
								204 103
								162 80
								165 26
								145 25
								126 82
								0 80
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 118
								123 169
								210 168
								319 135
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 17)
				(= northRoom 11)
				(= southRoom 25)
				(= eastRoom 18)
				(= westRoom 16)
			)
			((== curRoomNum 18)
				(= northRoom 18)
				(= southRoom 18)
				(= eastRoom 19)
				(= westRoom 17)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 65
								221 70
								170 63
								144 67
								141 102
								106 118
								0 102
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 144
								126 176
								204 176
								210 132
								254 119
								319 119
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 19)
				(= northRoom 19)
				(= southRoom 26)
				(= eastRoom 19)
				(= westRoom 18)
			)
			((== curRoomNum 23)
				(= northRoom 23)
				(= southRoom 34)
				(= eastRoom 24)
				(= westRoom 23)
			)
			((== curRoomNum 24)
				(= northRoom 16)
				(= southRoom 35)
				(= eastRoom 25)
				(= westRoom 23)
			)
			((== curRoomNum 25)
				(= northRoom 17)
				(= southRoom 36)
				(= eastRoom 25)
				(= westRoom 24)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 189
								203 189
								208 128
								234 83
								166 66
								153 39
								128 39
								149 89
								121 117
								0 109
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 145
								89 158
								140 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 26)
				(= northRoom 19)
				(= southRoom 42)
				(= eastRoom 27)
				(= westRoom 19)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 103
								198 103
								162 80
								156 26
								131 26
								131 80
								115 80
								58 110
								118 147
								124 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								216 189
								248 152
								319 136
								319 189
							yourself:
						)
				)
			)
			((== curRoomNum 27)
				(= northRoom 27)
				(= southRoom 27)
				(= eastRoom 28)
				(= westRoom 26)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 65
								221 70
								170 63
								144 67
								141 102
								106 118
								0 102
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 144
								126 176
								204 176
								210 132
								254 119
								319 119
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 33)
				(= northRoom 22)
				(= southRoom 45)
				(= eastRoom 34)
				(= westRoom 33)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 94
								160 61
								160 37
								126 37
								128 61
								143 79
								139 107
								85 147
								133 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								218 189
								206 143
								228 124
								319 136
								319 189
							yourself:
						)
				)
			)
			((== curRoomNum 34)
				(= northRoom 23)
				(= southRoom 51)
				(= eastRoom 35)
				(= westRoom 33)
			)
			((== curRoomNum 35)
				(= northRoom 24)
				(= southRoom 52)
				(= eastRoom 36)
				(= westRoom 34)
			)
			((== curRoomNum 36)
				(= northRoom 25)
				(= southRoom 53)
				(= eastRoom 36)
				(= westRoom 35)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								139 27
								128 99
								0 103
								0 0
								319 0
								319 189
								197 189
								200 140
								221 114
								163 71
								161 27
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 138
								98 146
								119 167
								121 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 42)
				(= northRoom 26)
				(= southRoom 56)
				(= eastRoom 43)
				(= westRoom 42)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 94
								160 61
								160 37
								126 37
								128 61
								143 79
								139 107
								85 147
								133 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								218 189
								206 143
								228 124
								319 136
								319 189
							yourself:
						)
				)
			)
			((== curRoomNum 43)
				(= northRoom 43)
				(= southRoom 57)
				(= eastRoom 43)
				(= westRoom 42)
			)
			((== curRoomNum 44)
				(= northRoom 44)
				(= southRoom 61)
				(= eastRoom 45)
				(= westRoom 44)
			)
			((== curRoomNum 51)
				(= northRoom 34)
				(= southRoom 63)
				(= eastRoom 52)
				(= westRoom 45)
			)
			((== curRoomNum 52)
				(= northRoom 35)
				(= southRoom 64)
				(= eastRoom 53)
				(= westRoom 51)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 67
								169 67
								154 36
								132 36
								145 95
								104 120
								0 101
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 148
								132 172
								238 172
								199 159
								197 147
								319 110
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 56)
				(= northRoom 42)
				(= southRoom 66)
				(= eastRoom 57)
				(= westRoom 54)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								135 82
								104 92
								0 88
								0 0
								319 0
								319 99
								198 99
								154 65
								154 26
								135 26
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								216 122
								319 122
								319 189
								201 189
								201 139
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 102
								106 122
								121 152
								121 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 57)
				(= northRoom 43)
				(= southRoom 67)
				(= eastRoom 58)
				(= westRoom 56)
			)
			((== curRoomNum 61)
				(= northRoom 44)
				(= southRoom 69)
				(= eastRoom 62)
				(= westRoom 60)
			)
			((== curRoomNum 62)
				(= northRoom 45)
				(= southRoom 70)
				(= eastRoom 63)
				(= westRoom 61)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 71
								178 72
								146 34
								127 34
								145 89
								123 114
								45 114
								0 91
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 141
								140 173
								140 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								208 137
								319 111
								319 189
								208 189
							yourself:
						)
				)
			)
			((== curRoomNum 63)
				(= northRoom 51)
				(= southRoom 71)
				(= eastRoom 64)
				(= westRoom 62)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								135 82
								104 92
								0 88
								0 0
								319 0
								319 99
								198 99
								154 65
								154 26
								135 26
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								216 122
								319 122
								319 189
								201 189
								201 139
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 102
								106 122
								121 152
								121 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 66)
				(= northRoom 56)
				(= southRoom 75)
				(= eastRoom 67)
				(= westRoom 65)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							121 148
							77 125
							83 87
							106 86
							137 79
							145 61
							175 61
							177 66
							221 92
							228 91
							231 122
							217 123
							182 153
							137 159
						yourself:
					)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 86
								216 86
								180 64
								180 27
								186 16
								157 16
								136 76
								100 85
								0 85
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 127
								72 127
								129 158
								129 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								189 189
								189 152
								220 127
								319 127
								319 189
							yourself:
						)
				)
			)
			((== curRoomNum 67)
				(= northRoom 57)
				(= southRoom 67)
				(= eastRoom 68)
				(= westRoom 66)
				(++ enter67)
				(if (== (ego edgeHit?) SOUTH)
					(= initEgoX 132)
					(= initEgoY 71)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							121 161
							104 124
							74 124
							74 87
							101 87
							120 65
							145 65
							149 68
							187 78
							222 78
							222 104
							208 106
							173 164
							122 164
						yourself:
					)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 74
								185 79
								152 69
								144 23
								130 23
								115 68
								78 82
								0 82
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 120
								89 161
								177 161
								319 117
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 68)
				(= northRoom 68)
				(= southRoom 68)
				(= eastRoom 68)
				(= westRoom 67)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							121 161
							104 124
							74 124
							74 87
							101 87
							120 65
							145 65
							149 68
							187 78
							222 78
							222 104
							208 106
							173 164
							122 164
						yourself:
					)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 189
								200 189
								0 189
								0 124
								141 124
								184 167
								218 148
								197 122
								217 81
								0 81
							yourself:
						)
				)
			)
			((== curRoomNum 69)
				(= northRoom 61)
				(= southRoom 69)
				(= eastRoom 70)
				(= westRoom 69)
			)
			((== curRoomNum 71)
				(= northRoom 63)
				(= southRoom 78)
				(= eastRoom 72)
				(= westRoom 70)
			)
			((== curRoomNum 72)
				(= northRoom 64)
				(= southRoom 79)
				(= eastRoom 73)
				(= westRoom 71)
			)
			((== curRoomNum 74)
				(= northRoom 65)
				(= southRoom 81)
				(= eastRoom 75)
				(= westRoom 73)
			)
			((== curRoomNum 75)
				(= northRoom 66)
				(= southRoom 75)
				(= eastRoom 75)
				(= westRoom 74)
			)
			((== curRoomNum 77)
				(= northRoom 70)
				(= southRoom 77)
				(= eastRoom 78)
				(= westRoom 76)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								149 104
								94 116
								0 100
								0 0
								319 0
								319 67
								183 67
								165 59
								157 29
								138 29
								138 59
								149 76
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 140
								92 140
								122 171
								201 171
								201 139
								221 123
								319 124
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 78)
				(= northRoom 71)
				(= southRoom 84)
				(= eastRoom 79)
				(= westRoom 77)
			)
			((== curRoomNum 79)
				(= northRoom 72)
				(= southRoom 85)
				(= eastRoom 80)
				(= westRoom 78)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 67
								169 67
								154 36
								132 36
								145 95
								104 120
								0 101
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 148
								132 172
								238 172
								199 159
								197 147
								319 110
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 80)
				(= northRoom 73)
				(= southRoom 80)
				(= eastRoom 81)
				(= westRoom 79)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								128 78
								109 89
								0 89
								0 0
								319 0
								319 104
								203 104
								167 65
								157 29
								138 29
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 109
								99 133
								122 171
								201 171
								201 139
								221 123
								319 124
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 81)
				(= northRoom 74)
				(= southRoom 87)
				(= eastRoom 82)
				(= westRoom 80)
			)
			((== curRoomNum 85)
				(= northRoom 85)
				(= southRoom 85)
				(= eastRoom 86)
				(= westRoom 84)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								93 101
								0 85
								0 0
								319 0
								319 99
								190 101
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 122
								106 122
								121 166
								203 166
								202 132
								319 132
								319 189
								0 189
							yourself:
						)
				)
			)
			((== curRoomNum 86)
				(= northRoom 86)
				(= southRoom 92)
				(= eastRoom 86)
				(= westRoom 85)
			)
			((== curRoomNum 92)
				(= northRoom 86)
				(= southRoom 92)
				(= eastRoom 92)
				(= westRoom 91)
			)
		)
		(cond 
			((OneOf curRoomNum 12 24 26 34 61 72 74 78)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							133 179
							133 148
							105 118
							105 94
							129 94
							134 71
							153 72
							234 130
							204 160
							204 180
							133 180
						yourself:
					)
				)
				(if (and (!= curRoomNum 12) (!= curRoomNum 26))
					(curRoom
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init:
									0 0
									319 0
									319 105
									208 105
									156 69
									160 27
									137 27
									123 88
									0 92
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									0 115
									109 150
									130 189
									0 189
								yourself:
							)
							((Polygon new:)
								type: PBarredAccess
								init:
									213 189
									319 136
									319 189
								yourself:
							)
					)
				)
			)
			((OneOf curRoomNum 36 56 63 77 80 85)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							124 141
							106 114
							105 94
							139 83
							172 83
							210 121
							193 138
							193 183
							124 183
						yourself:
					)
				)
			)
			((OneOf curRoomNum 18 25 27 33 42 52 62 79)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							143 166
							98 139
							98 117
							129 117
							148 92
							149 67
							175 67
							185 73
							262 76
							264 109
							200 137
							200 184
							143 184
						yourself:
					)
				)
			)
			((OneOf curRoomNum 17 35 51 57 71 81)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 96
								178 68
								152 42
								125 42
								148 89
								123 116
								0 83
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 140
								114 168
								133 189
								0 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								221 189
								319 145
								319 189
							yourself:
						)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							147 88
							226 88
							226 139
							212 139
							212 182
							137 182
							130 162
							99 147
							147 87
						yourself:
					)
				)
			)
			((OneOf curRoomNum 11 23 44)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								177 95
								131 113
								127 128
								35 154
								19 189
								0 189
								0 0
								319 0
								319 75
								248 81
							yourself:
						)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							125 185
							125 128
							190 95
							220 95
							235 85
							315 85
							315 102
							281 102
							218 129
							206 146
							195 169
							193 185
						yourself:
					)
				)
			)
			((OneOf curRoomNum 19 43 86)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 189
								196 189
								223 147
								176 109
								82 82
								0 75
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 118
								128 189
								0 189
							yourself:
						)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							126 181
							39 100
							45 84
							86 85
							174 107
							192 125
							192 183
							126 182
						yourself:
					)
				)
			)
			((== curRoomNum 69)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								108 6
								103 60
								86 82
								85 97
								70 117
								129 132
								207 147
								319 147
								319 189
								0 189
								0 95
								0 0
								319 0
								319 67
								271 67
								178 51
								176 23
								164 6
							yourself:
						)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							115 155
							102 125
							102 110
							143 87
							141 73
							158 72
							193 93
							218 93
							218 116
							173 155
						yourself:
					)
				)
			)
			((OneOf curRoomNum 75 92)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								165 0
								140 52
								0 79
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								0 114
								83 114
								131 137
								244 116
								211 52
								211 0
								319 0
								319 189
								0 189
							yourself:
						)
				)
				(= floorPoly
					((Polygon new:)
						type: PTotalAccess
						init:
							69 111
							68 68
							142 54
							161 15
							206 15
							206 54
							226 88
							227 110
							201 121
							128 131
							84 110
						yourself:
					)
				)
				(if (== (ego edgeHit?) SOUTH)
					(= initEgoX 170)
				)
			)
		)
		(forestFloor init:)
		(forestTrees init:)
		(forestRocks init:)
		(ego setScript: walkIn)
	)
	
	(method (doit)
		(super doit:)
		(if walkingIn
			(cond 
				(
					(and
						(== walkInDone 0)
						(or
							(and
								(== curRoomNum 77)
								(not Night)
								(!= prevRoomNum 76)
								(not (Btst fMetDryad))
							)
							(and
								(== curRoomNum 78)
								(not Night)
								(not (Btst fMetDryad))
								(!= prevRoomNum 77)
								(not (Btst fAntwerpInSky))
							)
							(and
								(== curRoomNum 67)
								(or (== enter67 2) (== enter67 6) (== enter67 9))
							)
						)
					)
					(HandsOff)
				)
				((< (ego y?) 55)
					(HandsOff)
					(if (== (ego script?) walkIn)
						(walkIn dispose:)
					)
					(= initEgoX (ego x?))
					(= initEgoY 47)
					(= nextRoom northRoom)
					(ego setScript: sExitAll)
				)
				((> (ego y?) 180)
					(HandsOff)
					(if (== (ego script?) walkIn)
						(walkIn dispose:)
					)
					(= initEgoX (ego x?))
					(= initEgoY 250)
					(= nextRoom southRoom)
					(ego setScript: sExitAll)
				)
				((< (ego x?) 20)
					(HandsOff)
					(if (== (ego script?) walkIn)
						(walkIn dispose:)
					)
					(= initEgoX -20)
					(= initEgoY (ego y?))
					(= nextRoom westRoom)
					(ego setScript: sExitAll)
				)
				((> (ego x?) 300)
					(HandsOff)
					(if (== (ego script?) walkIn)
						(walkIn dispose:)
					)
					(= initEgoX 335)
					(= initEgoY (ego y?))
					(= nextRoom eastRoom)
					(ego setScript: sExitAll)
				)
				((and (== (ego script?) walkIn) (== (ego mover?) 0))
					(ego setScript: 0)
				)
			)
		else
			(HandsOff)
			(if
				(and
					(<= 30 (ego x?))
					(<= (ego x?) 290)
					(<= 70 (ego y?))
					(<= (ego y?) 180)
				)
				(= walkingIn TRUE)
				(if (or (== (ego script?) 0) (== (ego script?) walkIn))
					(HandsOn)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_CALM
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 1 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_TRIGGER
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 1 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_OPEN
					(messager say: N_FOREST V_MAGIC NULL 2 0 FOREST)
					(return TRUE)
				)
				(V_DETECT
					(messager say: N_FOREST V_MAGIC NULL 3 0 FOREST)
					(return TRUE)
				)
				(V_DAZZLE
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 4 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(70
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 4 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_FLAME
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 4 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_ZAP
					(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					(if (or (ego has: iDagger) (ego has: iSword))
						(messager say: N_FOREST V_MAGIC NULL 5 0 FOREST)
					else
						(messager say: N_FOREST V_MAGIC NULL 6 0 FOREST)
					)
					(return TRUE)
				)
				(V_FETCH
					(if
						(not
							(OneOf curRoomNum
								11 12 17 18 19 23 24 25 26 27 33 34 35 36
								42 43 44 51 52 56 57 61 62 63 69 71 72 74
								75 79 80 81 85 86 92
							)
						)
						(messager say: N_FOREST V_MAGIC NULL 7 0 FOREST)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(V_LOOK
					(if atClearing
						(messager say: N_FOREST V_LOOK C_AT_CLEARING 0 0 FOREST)
						(return TRUE)
					else
						(messager say: N_FOREST V_LOOK NULL 0 0 FOREST)
						(return TRUE)
					)
				)
				(V_MONEY
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_RATIONS
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_SWORD
					(messager say: N_FOREST NULL 4 1 0 FOREST)
					(return TRUE)
				)
				(V_CHAINMAIL
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_LEATHER
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_SHIELD
					(messager say: N_FOREST NULL 4 1 0 FOREST)
					(return TRUE)
				)
				(V_DAGGER
					(ThrowKnife 0)
					(return TRUE)
				)
				(V_LOCKPICK
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_THIEFKIT
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_THIEFLICENSE
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_ROCK
					(ThrowRock 0)
					(return TRUE)
				)
				(V_FLASK
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_HEALING
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_MANA
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_VIGOR
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_DISENCHANT
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_BRASSKEY
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_MAGICGEM
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_RING
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_GHOSTOIL
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_MAGICMIRROR
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(85
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_MANDRAKE
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_FRUIT
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_VEGETABLES
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_ACORN
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_SEED
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_FLOWERS
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_GREENFUR
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_FAIRYDUST
					(messager say: N_FOREST NULL C_DONT_DROP_THAT 1 0 FOREST)
					(return TRUE)
				)
				(V_WATER
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_MUSHROOM
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_VASE
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_CANDELABRA
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_CANDLESTICKS
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_MUSICBOX
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_PEARLS
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_PAGETURNER
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(76
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_PAPER
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_CHEETAURCLAW
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_TROLLBEARD
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_SMELL
					(messager say: N_FOREST V_SMELL 0 1 0 FOREST)
					(return TRUE)
				)
				(V_GOLD
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(54
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(55
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(56
					(messager say: N_FOREST NULL C_LITTERBUG 1 0 FOREST)
					(return TRUE)
				)
				(V_DO
					(messager say: N_FOREST NULL C_NOTHING_HAPPENS 1 0 FOREST)
					(return TRUE)
				)
				;commented out duplicate case
				;(V_DO
				;	(messager say: N_FOREST NULL C_NOTHING_HAPPENS 1 0 FOREST)
				;	(return TRUE)
				;s)
				(V_TALK
					(messager say: N_FOREST NULL C_NOTHING_HAPPENS 1 0 FOREST)
					(return TRUE)
				)
				(V_ALTTALK
					(messager say: N_FOREST NULL C_NOTHING_HAPPENS 1 0 FOREST)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance forestFloor of Feature
	(properties
		y 1
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(super init: ftrInitializer)
		(= onMeCheck floorPoly)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(onMeCheck dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(if atClearing
						(messager say: N_FLOOR V_LOOK C_AT_CLEARING 0 0 FOREST)
						(return TRUE)
					else
						(messager say: N_FLOOR V_LOOK NULL 0 0 FOREST)
						(return TRUE)
					)
				)
				(V_DO
					(ego setScript: (ScriptID GETROCK 0))
				)
				(V_WALK
					(super doVerb: theVerb &rest)
				)
				(V_STAND
					(super doVerb: theVerb &rest)
				)
				(V_DAGGER
					(ThrowKnife 0)
					(return TRUE)
				)
				(V_ROCK
					(ThrowRock 0)
					(return TRUE)
				)
				(else 
					(forestRegion doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance forestRocks of Feature
	(properties
		x 319
		y 1
		z 188
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cLGREY
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(super init: ftrInitializer)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(switch (= randomJoke (Random 0 12))
						(0
							(messager say: N_ROCKS V_LOOK C_JOKE1 0 0 FOREST)
							(return TRUE)
						)
						(1
							(messager say: N_ROCKS V_LOOK C_JOKE2 0 0 FOREST)
							(return TRUE)
						)
						(2
							(messager say: N_ROCKS V_LOOK C_JOKE3 0 0 FOREST)
							(return TRUE)
						)
						(3
							(messager say: N_ROCKS V_LOOK C_JOKE4 0 0 FOREST)
							(return TRUE)
						)
						(4
							(messager say: N_ROCKS V_LOOK C_JOKE5 0 0 FOREST)
							(return TRUE)
						)
						(5
							(messager say: N_ROCKS V_LOOK C_JOKE6 0 0 FOREST)
							(return TRUE)
						)
						(6
							(messager say: N_ROCKS V_LOOK C_JOKE7 0 0 FOREST)
							(return TRUE)
						)
						(7
							(messager say: N_ROCKS V_LOOK C_JOKE8 0 0 FOREST)
							(return TRUE)
						)
						(8
							(messager say: N_ROCKS V_LOOK C_JOKE9 0 0 FOREST)
							(return TRUE)
						)
						(9
							(messager say: N_ROCKS V_LOOK C_JOKE10 0 0 FOREST)
						)
						(10
							(messager say: N_ROCKS V_LOOK C_JOKE11 0 0 FOREST)
							(return TRUE)
						)
						(11
							(messager say: N_ROCKS V_LOOK C_JOKE12 0 0 FOREST)
							(return TRUE)
						)
						(12
							(messager say: N_ROCKS V_LOOK C_JOKE13 0 0 FOREST)
							(return TRUE)
						)
					)
				)
				(V_DO
					(messager say: N_ROCKS V_DO NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_TALK
					(messager say: N_ROCKS V_TALK NULL 1 0 FOREST)
					(return TRUE)
				)
;				Commented out functionally identical case value
;				(V_ALTTALK
;					(messager say: 3 2 0 1 0 FOREST)
;					(return TRUE)
;				)
				(V_SWORD
					(messager say: N_ROCKS V_DAGGER NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_DAGGER
					(messager say: N_ROCKS V_DAGGER NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_WALK
					(super doVerb: theVerb &rest)
				)
				(V_DROP
					(super doVerb: theVerb &rest)
				)
				(71
					(super doVerb: theVerb &rest)
				)
				(else 
					(forestRegion doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance forestTrees of Feature
	(properties
		x 319
		y 1
		z 188
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cBROWN
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(super init: ftrInitializer)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(cond 
						((<= (= randomJoke (Random 0 99)) 10)
							(messager say: N_TREES V_LOOK C_JOKE6 0 0 FOREST)
						)
						((<= randomJoke 20)
							(messager say: N_TREES V_LOOK C_JOKE12 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 30)
							(messager say: N_TREES V_LOOK C_JOKE10 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 38)
							(messager say: N_TREES V_LOOK C_JOKE4 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 56)
							(messager say: N_TREES V_LOOK C_JOKE3 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 64)
							(messager say: N_TREES V_LOOK C_JOKE8 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 72)
							(messager say: N_TREES V_LOOK C_JOKE7 0 0 FOREST)
							(return TRUE)
						)
						((<= randomJoke 80)
							(messager say: N_TREES V_LOOK C_JOKE1 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 81)
							(messager say: N_TREES V_LOOK C_JOKE5 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 82)
							(messager say: N_TREES V_LOOK C_JOKE9 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 83)
							(messager say: N_TREES V_LOOK C_JOKE2 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 84)
							(messager say: N_TREES V_LOOK C_JOKE11 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 85)
							(messager say: N_TREES V_LOOK C_JOKE20 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 86)
							(messager say: N_TREES V_LOOK C_JOKE16 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 87)
							(messager say: N_TREES V_LOOK C_JOKE15 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 88)
							(messager say: N_TREES V_LOOK C_JOKE19 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 89)
							(messager say: N_TREES V_LOOK C_JOKE18 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 90)
							(messager say: N_TREES V_LOOK C_JOKE14 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 91)
							(messager say: N_TREES V_LOOK C_JOKE17 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 92)
							(messager say: N_TREES V_LOOK C_JOKE28 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 93)
							(messager say: N_TREES V_LOOK C_JOKE23 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 94)
							(messager say: N_TREES V_LOOK C_JOKE27 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 95)
							(messager say: N_TREES V_LOOK C_JOKE26 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 96)
							(messager say: N_TREES V_LOOK C_JOKE22 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 97)
							(messager say: N_TREES V_LOOK C_JOKE21 0 0 FOREST)
							(return TRUE)
						)
						((== randomJoke 99)
							(messager say: N_TREES V_LOOK C_JOKE25 0 0 FOREST)
							(return TRUE)
						)
					)
				)
				(V_DO
					(messager say: N_TREES V_DO NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_FRUIT
					(messager say: N_TREES V_FRUIT NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_TALK
					(messager say: N_TREES V_TALK NULL 1 0 FOREST)
					(return TRUE)
				)
;				(V_ALTTALK
;					(messager say: N_TREES V_TALK NULL 1 0 FOREST)
;					(return TRUE)
;				)
				(V_SWORD
					(messager say: N_TREES V_SWORD NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_DAGGER
					(messager say: N_TREES V_DAGGER NULL 1 0 FOREST)
					(return TRUE)
				)
				(V_WALK
					(super doVerb: theVerb &rest)
				)
				(V_STAND
					(super doVerb: theVerb &rest)
				)
				(else 
					(forestRegion doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance walkIn of Script
	(method (dispose)
		(= walkInDone TRUE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
					(and
						initEgoX
						initEgoY
						(not
							(OneOf prevRoomNum
								vBear vMinotaur vSaurus vMantray vCheetaur vGoblin
								vOgre vTroll vDragon vBrigand vBrigandLeader
							)
						)
					)
					(ego posn: initEgoX initEgoY)
					(cond 
						((Btst fFaeryAttention)
							(= toX 160)
							(= toY 140)
						)
						((>= initEgoY 170)
							(= toX 160)
							(= toY 10)
						)
						((<= initEgoY 90)
							(= toX 160)
							(= toY 181)
							(ego loop: loopS)
						)
						((<= initEgoX 30)
							(= toX 310)
							(= toY 110)
						)
						((>= initEgoX 300)
							(= toX 10)
							(= toY 110)
						)
						(else
							(= toX 160)
							(= toY 150)
						)
					)
				else
					(ego posn: 160 140)
				)
				(if
					(or
						(not monsterNum)
						(and (== curRoomNum 80) (> brunoTimer 260))
					)
					(ego init:)
					(self cue:)
				)
				(= ticks 30)
			)
			(1
				(= ticks 1)
			)
			(2
				(if
					(or
						(Btst fFaeryAttention)
						(and
							(== curRoomNum 77)
							(not Night)
							(!= prevRoomNum 76)
							(not (Btst fMetDryad))
						)
						(and
							(== curRoomNum 78)
							(not Night)
							(not (Btst fMetDryad))
							(!= prevRoomNum 77)
							(not (Btst fAntwerpInSky))
						)
						(and
							(== curRoomNum 67)
							(or (== enter67 2) (== enter67 6) (== enter67 9))
						)
					)
					(HandsOff)
				else
					(NormalEgo)
				)
				(if (and toX toY)
					(ego setMotion: PolyPath toX toY self)
				else
					(ego setMotion: PolyPath 160 150 self)
				)
			)
			(3
				(self changeState: 5)
			)
			(4
				(ego setMotion: PolyPath 160 150 self)
			)
			(5 (self dispose:))
		)
	)
)

(instance sExitAll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= walkingIn 0)
				(ego
					setMotion: PolyPath initEgoX initEgoY
					self
				)
			)
			(1
				(= initEgoX 0)
				(= initEgoY 0)
				(= toX 0)
				(= toY 0)
				(if (and Night (== nextRoom 70))
					(= nextRoom 170)
				)
				(curRoom newRoom: nextRoom)
				(self dispose:)
			)
		)
	)
)
