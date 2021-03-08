;;; Sierra Script 1.0 - (do not remove this comment)
(script# 403)
(include game.sh) (include "400.shm")
(use Main)
(use Polygon)
(use Actor)
(use System)

(public
	InitAddTos 0
)

(instance InitAddTos of Code
	
	(method (init param1 param2 param3 param4 param5 param6 &tmp obj)
		(= obj
			((View new:)
				view: 400
				signal: (| fixedLoop skipCheck viewAdded)
			)
		)
		(switch param1
			(0
				(obj cel: 0)
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj loop: 3 x: 120 y: 149 noun: N_ROCK2)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													186 144
													161 153
													75 153
													65 141
													94 134
													159 134
												yourself:
											)
									)
								)
								(1
									(obj loop: 3 x: 0 y: 105 noun: N_ROCK1)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 90
													51 92
													65 99
													53 108
													0 111
												yourself:
											)
									)
								)
								(2
									(obj loop: 3 x: 290 y: 116 noun: N_ROCK3)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 118
													243 118
													237 107
													274 102
													319 102
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj loop: 2 x: 199 y: 149 noun: N_ROCK4)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													160 134
													225 134
													254 141
													244 153
													161 153
													133 144
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 x: 319 y: 105 noun: N_ROCK5)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 111
													266 108
													254 99
													268 92
													319 90
												yourself:
											)
									)
								)
								(2
									(obj loop: 2 x: 29 y: 116 noun: N_ROCK6)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 102
													45 102
													82 107
													76 118
													0 118
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj loop: 3 x: 202 y: 189 noun: N_ROCK7)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													266 181
													250 189
													155 189
													147 181
													201 171
													243 173
												yourself:
											)
									)
								)
								(1
									(obj loop: 3 x: 319 y: 94 noun: N_ROCK8)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 98
													296 98
													270 93
													270 84
													319 84
												yourself:
											)
									)
								)
								(2
									(obj loop: 3 x: 0 y: 171 noun: N_ROCK9)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 149
													41 149
													65 163
													56 172
													0 176
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj loop: 2 x: 117 y: 189 noun: N_ROCK10)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													76 173
													118 171
													172 181
													164 189
													69 189
													53 181
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 x: 0 y: 94 noun: N_ROCK11)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 84
													49 84
													49 93
													23 98
													0 98
												yourself:
											)
									)
								)
								(2
									(obj loop: 2 x: 319 y: 171 noun: N_ROCK12)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 176
													263 172
													254 163
													278 149
													319 149
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
			(1
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj loop: 2 cel: 2 x: 173 y: 167 noun: N_ROCK13)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													206 154
													189 169
													145 170
													146 155
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 cel: 2 x: 217 y: 127 noun: N_ROCK14)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													254 122
													223 133
													189 128
													220 115
												yourself:
											)
									)
								)
								(2
									(obj loop: 2 cel: 1 x: 115 y: 97 noun: N_ROCK15)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													138 94
													113 102
													93 93
													117 89
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj loop: 2 cel: 2 x: 146 y: 167 noun: N_ROCK16)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													173 155
													174 170
													130 169
													113 154
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 cel: 1 x: 102 y: 127 noun: N_ROCK17)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													99 115
													130 128
													96 133
													65 122
												yourself:
											)
									)
								)
								(2
									(obj loop: 2 cel: 1 x: 204 y: 97 noun: N_ROCK18)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													202 89
													226 93
													206 102
													181 94
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(obj loop: 2 cel: 1 x: 204 y: 97 noun: N_ROCK19)
						(curRoom
							addObstacle:
								((Polygon new:)
									type: PBarredAccess
									init:
										202 89
										226 93
										206 102
										181 94
									yourself:
								)
						)
					)
				)
			)
			(2
				(obj cel: 0)
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 268
										y: 165
										noun: N_TREE3
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													297 162
													273 178
													246 165
													271 145
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 195
										y: 104
										noun: N_TREE4
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													222 101
													200 116
													176 103
													199 85
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 0
										y: 155
										noun: N_TREE1
									)
									(curRoom
										addObstacle: ((Polygon new:)
											type: PBarredAccess
											init:
												0 142
												35 151
												0 165
											yourself:
										)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 51
										y: 165
										noun: N_TREE2
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													48 145
													73 165
													46 178
													22 162
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 124
										y: 104
										noun: N_TREE5
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													120 85
													143 103
													119 116
													97 101
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 319
										y: 155
										noun: N_TREE6
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 165
													284 151
													319 142
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 187
										y: 168
										noun: N_TREE7
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													226 164
													202 177
													163 177
													158 162
													190 154
													225 158
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 99
										y: 115
										noun: N_TREE8
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													118 110
													98 124
													74 113
													98 99
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 288
										y: 137
										noun: N_TREE9
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													307 132
													287 147
													264 136
													284 118
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 132
										y: 173
										noun: N_TREE10
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													94 158
													129 154
													161 162
													156 177
													117 177
													93 164
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 220
										y: 115
										noun: N_TREE11
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													221 99
													245 113
													221 124
													201 110
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 31
										y: 137
										noun: N_TREE12
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													35 118
													55 136
													32 147
													12 132
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
			(3
				(obj cel: 0)
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 0
										y: 155
										noun: N_TREE3
									)
									(curRoom
										addObstacle: ((Polygon new:)
											type: PBarredAccess
											init:
												0 142
												35 151
												0 165
											yourself:
										)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 268
										y: 165
										noun: N_TREE1
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													297 162
													273 178
													246 165
													271 145
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 195
										y: 104
										noun: N_TREE1
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													222 101
													200 116
													176 103
													199 85
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 319
										y: 155
										noun: N_TREE2
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 165
													284 151
													319 142
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 51
										y: 165
										noun: N_TREE5
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													48 145
													73 165
													46 178
													22 162
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 124
										y: 104
										noun: N_TREE6
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													120 85
													143 103
													119 116
													97 101
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 288
										y: 137
										noun: N_TREE7
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													307 132
													287 147
													264 136
													284 118
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 187
										y: 168
										noun: N_TREE8
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													226 164
													202 177
													163 177
													158 162
													190 154
													225 158
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 99
										y: 115
										noun: N_TREE9
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													118 110
													98 124
													74 113
													98 99
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 220
										y: 115
										noun: N_TREE10
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													221 99
													245 113
													221 124
													201 110
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 31
										y: 137
										noun: N_TREE11
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													35 118
													55 136
													32 147
													12 132
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										x: 132
										y: 168
										noun: N_TREE12
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													94 158
													129 154
													161 162
													156 177
													117 177
													93 164
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
			(4
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 13
										y: 86
										noun: N_TREE3
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													2 84
													13 77
													28 83
													15 91
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 300
										y: 69
										noun: N_TREE4
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													314 66
													303 75
													291 68
													302 61
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 159
										y: 75
										noun: N_TREE1
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													172 73
													160 81
													147 73
													161 66
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 306
										y: 86
										noun: N_TREE2
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													304 91
													291 83
													306 77
													317 84
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 19
										y: 69
										noun: N_TREE5
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													17 61
													28 68
													16 75
													5 66
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 160
										y: 75
										noun: N_TREE6
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													158 66
													172 73
													159 81
													147 73
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 317
										y: 65
										noun: N_TREE7
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													300 65
													319 55
													319 75
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 61
										y: 88
										noun: N_TREE8
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													40 84
													60 70
													80 84
													60 100
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 162
										y: 63
										noun: N_TREE9
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													142 63
													162 50
													182 63
													162 74
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 2
										y: 65
										noun: N_TREE10
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 55
													15 65
													0 75
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 258
										y: 88
										noun: N_TREE11
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													250 88
													260 78
													270 88
													260 98
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 157
										y: 63
										noun: N_TREE12
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													147 63
													157 53
													167 63
													157 73
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
			(5
				(switch param6
					(0
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 159
										y: 75
										noun: N_TREE3
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													172 73
													160 81
													147 73
													161 66
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 13
										y: 86
										noun: N_TREE4
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													2 84
													13 77
													28 83
													15 91
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 300
										y: 69
										noun: N_TREE1
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													314 66
													303 75
													291 68
													302 61
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 19
										y: 69
										noun: N_TREE2
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													17 61
													28 68
													16 75
													5 66
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 160
										y: 75
										noun: N_TREE5
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													158 66
													172 73
													159 81
													147 73
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 306
										y: 86
										noun: N_TREE6
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													304 91
													291 83
													306 77
													317 84
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 61
										y: 88
										noun: N_TREE7
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													50 88
													61 78
													70 88
													61 98
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 162
										y: 63
										noun: N_TREE8
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													152 63
													162 53
													172 63
													162 73
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 317
										y: 65
										noun: N_TREE9
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													307 65
													319 55
													319 75
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 258
										y: 88
										noun: N_TREE10
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													248 88
													258 78
													268 88
													258 98
												yourself:
											)
									)
								)
								(1
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 2
										x: 157
										y: 63
										noun: N_TREE11
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													147 63
													157 35
													167 63
													157 73
												yourself:
											)
									)
								)
								(2
									(obj
										loop: (mod (+ param4 param5) 2)
										cel: 1
										x: 2
										y: 65
										noun: N_TREE12
									)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 55
													12 65
													0 75
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
			(6
				(obj
					signal: (| (obj signal?) fixPriOn)
					priority: 0
					noun: N_ROOM
				)
				(switch param6
					(0
						(if param3
							(switch (mod param2 2)
								(0
									(obj x: 181 y: 147 loop: 5 cel: 0)
								)
								(1
									(obj x: 160 y: 167 loop: 5 cel: 1)
								)
							)
						else
							(switch (mod param2 2)
								(0
									(obj x: 138 y: 147 loop: 4 cel: 0)
								)
								(1
									(obj x: 159 y: 167 loop: 4 cel: 1)
								)
							)
						)
					)
					(1 (obj x: -500 y: -500))
				)
			)
			(7
				(obj cel: 4 noun: N_LOG)
				(switch param6
					(0
						(if param3
							(switch (mod param2 2)
								(0
									(obj loop: 3 x: 90 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													92 189
													53 177
													61 167
													151 189
												yourself:
											)
									)
								)
								(1
									(obj loop: 3 x: 15 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													25 189
													0 182
													0 169
													68 189
												yourself:
											)
									)
								)
							)
						else
							(switch (mod param2 2)
								(0
									(obj loop: 2 x: 229 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													168 189
													258 167
													266 177
													227 189
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 x: 304 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													251 189
													319 169
													319 182
													294 189
												yourself:
											)
									)
								)
							)
						)
					)
					(1
						(if param3
							(switch (mod param2 3)
								(0
									(obj loop: 2 x: 297 y: 179)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													319 184
													264 167
													276 155
													319 169
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 x: 107 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													112 189
													71 178
													80 169
													161 189
												yourself:
											)
									)
								)
								(2
									(obj loop: 3 x: 0 y: 189)
									(curRoom
										addObstacle: ((Polygon new:)
											type: PBarredAccess
											init:
												0 189
												0 171
												55 189
											yourself:
										)
									)
								)
							)
						else
							(switch (mod param2 3)
								(0
									(obj loop: 2 x: 22 y: 179)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													0 169
													43 155
													55 167
													0 184
												yourself:
											)
									)
								)
								(1
									(obj loop: 2 x: 212 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													158 189
													239 169
													248 178
													207 189
												yourself:
											)
									)
								)
								(2
									(obj loop: 3 x: 319 y: 189)
									(curRoom
										addObstacle:
											((Polygon new:)
												type: PBarredAccess
												init:
													264 189
													319 171
													319 189
												yourself:
											)
									)
								)
							)
						)
					)
				)
			)
		)
		(obj init:)
	)
)
