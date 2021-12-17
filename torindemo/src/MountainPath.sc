;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40700)
(include sci.sh)
(use Main)
(use ScrollExit)
(use soBooglePouch)
(use TPRoom)
(use TPSound)
(use List)
(use ExitFeature)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Motion)

(public
	roLavaMountain 0
)

(local
	local0
	gSel_1206Sel_16
	local2
)
(procedure (localproc_0202 param1 &tmp temp0)
	(if (curRoom obstacles?)
		((curRoom obstacles?) release:)
	else
		(curRoom obstacles: (List new:))
	)
	(curRoom addObstacle: oRoomPolygon)
	(features dispose: init:)
	(= temp0 0)
	(while (< temp0 argc)
		([param1 temp0] init:)
		(++ temp0)
	)
	(if (proc64018_4 ego (ego x?) (ego y?))
		(MonoOut {Dest %d %d is outside} (ego x?) (ego y?))
	)
)

(procedure (localproc_0458)
	(oRoomPolygon
		type: 3
		init:
			1892
			250
			1840
			267
			1786
			260
			1606
			175
			1603
			158
			1629
			153
			1688
			137
			1757
			130
			1834
			129
			1882
			132
			1896
			133
			1896
			127
			1800
			125
			1760
			115
			1708
			107
			1664
			101
			1611
			95
			1583
			94
			1586
			90
			1656
			86
			1748
			85
			1786
			86
			1786
			80
			1696
			83
			1590
			87
			1527
			94
			1507
			100
			1517
			104
			1520
			99
			1573
			95
			1630
			100
			1728
			113
			1792
			127
			1725
			128
			1637
			137
			1582
			155
			1564
			157
			1473
			136
			1416
			131
			1417
			123
			1493
			111
			1511
			106
			1494
			91
			1451
			83
			1447
			93
			1491
			96
			1493
			104
			1491
			107
			1451
			115
			1406
			123
			1404
			131
			1451
			137
			1489
			142
			1537
			155
			1633
			200
			1753
			295
			1893
			283
	)
	(localproc_0202 foA1 foA2 foA3 foA4 foA5)
)

(procedure (localproc_0893)
	(oRoomPolygon
		type: 3
		init:
			1456
			30
			1405
			26
			1414
			30
			1454
			32
			1426
			32
			1378
			30
			1323
			30
			1290
			36
			1269
			40
			1270
			44
			1299
			36
			1320
			33
			1389
			33
			1450
			35
			1461
			36
	)
	(localproc_0202 foB1 foB2)
)

(procedure (localproc_0a3f)
	(oRoomPolygon
		type: 3
		init:
			1207
			125
			1190
			127
			1166
			127
			1149
			126
			1143
			125
			1122
			125
			1109
			125
			1101
			120
			1094
			121
			1066
			122
			1062
			114
			1068
			104
			1076
			98
			1098
			110
			1101
			104
			1075
			96
			1063
			101
			1056
			122
			1068
			124
			1101
			122
			1109
			128
			1145
			127
			1159
			130
			1213
			128
	)
	(localproc_0202 foC1 foC2)
)

(procedure (localproc_0c21)
	(oRoomPolygon
		type: 3
		init:
			1375
			293
			1332
			278
			1315
			266
			1289
			254
			1257
			249
			1252
			204
			1259
			191
			1294
			181
			1304
			176
			1338
			170
			1337
			163
			1271
			158
			1238
			155
			1237
			150
			1244
			145
			1316
			130
			1315
			122
			1281
			112
			1262
			98
			1257
			89
			1226
			74
			1201
			43
			1198
			29
			1202
			24
			1230
			24
			1230
			19
			1203
			21
			1187
			27
			1190
			53
			1228
			80
			1256
			93
			1263
			102
			1283
			116
			1312
			125
			1310
			128
			1237
			145
			1224
			151
			1107
			164
			1068
			172
			1065
			165
			1086
			150
			1080
			148
			1056
			146
			1048
			141
			1030
			139
			1029
			132
			1034
			129
			1099
			137
			1103
			131
			1030
			127
			1014
			130
			1016
			136
			1032
			143
			1050
			144
			1071
			151
			1051
			167
			1055
			173
			1072
			175
			1123
			165
			1226
			153
			1244
			158
			1314
			164
			1316
			169
			1254
			187
			1229
			240
			1240
			255
			1288
			259
			1339
			286
			1308
			307
			1308
			311
			1423
			311
			1408
			287
	)
	(localproc_0202 foD1 foD2)
)

(procedure (localproc_0f39)
	(oRoomPolygon type: 3 init: 1083 22 930 24 930 27 1085 25)
	(localproc_0202 foE1 foE2)
)

(procedure (localproc_10aa)
	(oRoomPolygon
		type: 3
		init: 701 45 681 43 661 21 625 0 634 0 666 20 684 40 704 41
	)
	(localproc_0202 foF1 foF2)
)

(procedure (localproc_1226)
	(oRoomPolygon type: 3 init: 120 33 94 35 84 49 115 48)
	(localproc_0202 foG1 foG2)
)

(procedure (localproc_1390)
	(oRoomPolygon
		type: 3
		init: 223 119 172 119 172 121 223 121
	)
	(localproc_0202 foH1 foH2)
)

(procedure (localproc_1503)
	(oRoomPolygon
		type: 3
		init:
			153
			221
			100
			250
			87
			244
			84
			228
			116
			206
			110
			197
			65
			236
			69
			246
			389
			332
			566
			240
			646
			217
			645
			217
			653
			215
			701
			246
			727
			256
			774
			257
			789
			242
			833
			237
			849
			218
			870
			212
			905
			215
			928
			221
			948
			231
			955
			220
			968
			229
			977
			219
			990
			220
			999
			221
			1011
			207
			1048
			199
			1047
			188
			1018
			199
			1004
			204
			994
			215
			976
			217
			968
			226
			954
			215
			947
			227
			925
			217
			899
			211
			876
			208
			846
			213
			783
			237
			771
			251
			730
			254
			704
			245
			679
			229
			658
			215
			571
			212
			529
			241
			400
			297
			357
			302
			284
			295
			190
			272
			192
			262
			207
			253
			290
			242
			298
			240
			298
			232
			271
			227
			212
			224
			211
			228
			283
			234
			282
			238
			208
			248
			173
			267
			131
			256
			137
			244
			171
			231
	)
	(localproc_0202 foI1 foI2 foI3 foI4)
)

(procedure (localproc_1961)
	(oRoomPolygon
		type: 3
		init:
			1033
			163
			975
			158
			962
			167
			950
			163
			934
			166
			925
			155
			906
			162
			900
			155
			878
			163
			876
			158
			857
			165
			851
			161
			830
			169
			822
			161
			811
			173
			797
			174
			787
			163
			767
			174
			762
			166
			744
			172
			726
			160
			724
			150
			705
			152
			704
			147
			726
			146
			730
			156
			744
			169
			764
			163
			769
			170
			790
			160
			798
			172
			809
			164
			782
			146
			779
			137
			783
			124
			799
			111
			821
			102
			872
			83
			877
			77
			875
			73
			858
			74
			857
			68
			877
			70
			878
			77
			874
			85
			799
			113
			791
			120
			785
			132
			785
			142
			793
			149
			812
			161
			810
			168
			820
			158
			827
			159
			830
			165
			850
			159
			858
			163
			877
			156
			880
			161
			900
			153
			907
			160
			927
			152
			936
			164
			952
			160
			962
			165
			976
			155
			1034
			160
	)
	(localproc_0202 foJ1 foJ2 foJ3)
)

(procedure (localproc_1cfa)
	(oRoomPolygon
		type: 3
		init: 741 100 675 105 675 108 743 102
	)
	(localproc_0202 foK1)
)

(procedure (localproc_1dd6)
	(oRoomPolygon
		type: 3
		init: 741 100 675 105 675 108 743 102
	)
	(localproc_0202 foL1 foL2)
)

(procedure (localproc_1f4a)
	(oRoomPolygon
		type: 3
		init:
			233
			215
			247
			183
			290
			155
			329
			140
			378
			134
			418
			134
			461
			138
			517
			149
			524
			150
			523
			145
			514
			139
			468
			121
			448
			111
			420
			103
			379
			89
			368
			85
			363
			78
			365
			69
			381
			64
			417
			59
			621
			57
			622
			54
			411
			57
			364
			67
			360
			78
			367
			87
			448
			114
			521
			145
			521
			147
			513
			147
			464
			137
			398
			127
			350
			128
			317
			134
			262
			154
			216
			176
			199
			225
	)
	(localproc_0202 foM1 foM2)
)

(procedure (localproc_2184)
	(oRoomPolygon
		type: 3
		init:
			814
			11
			862
			26
			881
			35
			894
			45
			897
			44
			894
			40
			886
			29
			886
			32
			895
			44
			882
			34
			861
			24
			813
			9
			776
			2
			739
			-2
			753
			1
	)
	(localproc_0202 foN1 foN2)
)

(procedure (localproc_2328)
	(oRoomPolygon type: 3 init: 393 54 261 57 261 59 394 56)
	(localproc_0202 foO1 foO2)
)

(procedure (localproc_249a)
	(oRoomPolygon
		type: 3
		init: 313 86 278 84 257 82 235 83 199 86 199 83 257 79 315 84
	)
	(localproc_0202 foP1 foP2)
)

(procedure (localproc_261f)
	(oRoomPolygon
		type: 3
		init: 313 86 278 84 257 82 235 83 199 86 199 83 257 79 315 84
	)
	(localproc_0202 foQ1 foQ2 foWinner)
)

(class MountainPath of ExitFeature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		BAD_SELECTOR -1
	)
	
	(method (init)
		(super init: &rest)
		(switch BAD_SELECTOR
			(0
				(self setSpeedFraction: (ScriptID 64006 3))
			)
			(1
				(self setSpeedFraction: (ScriptID 64006 4))
			)
			(2
				(self setSpeedFraction: (ScriptID 64006 2))
			)
			(3
				(self setSpeedFraction: (ScriptID 64006 1))
			)
			(4
				(self setSpeedFraction: (ScriptID 64006 7))
			)
			(5
				(self setSpeedFraction: (ScriptID 64006 8))
			)
			(6
				(self setSpeedFraction: (ScriptID 64006 5))
			)
			(7
				(self setSpeedFraction: (ScriptID 64006 6))
			)
		)
		(features add: self)
	)
	
	(method (doVerb)
		(MonoOut {%s exit} name)
		(ego setMotion: PolyPath x y self 2)
	)
	
	(method (cue)
		(MonoOut {MountainPath super cued})
	)
)

(instance oRoomPolygon of Polygon
	(properties)
)

(instance foA1 of MountainPath
	(properties
		x 1863
		y 240
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1853 209 1853 267 1896 267 1896 207)
	)
	
	(method (cue)
		(theGame handsOff:)
		(curRoom newRoom: -24936)
	)
)

(instance foA2 of MountainPath
	(properties
		x 1910
		y 130
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1874 117 1874 138 1896 141 1897 114)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foA3 x?) (foA3 y?) (ScriptID 64020 0)
		)
	)
)

(instance foA3 of MountainPath
	(properties
		x 1772
		y 82
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1784 65 1784 97 1761 97 1761 65)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foA2 x?) (foA2 y?) (ScriptID 64020 0)
		)
	)
)

(instance foA4 of MountainPath
	(properties
		x 1510
		y 101
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1528 82 1544 102 1522 109 1503 89)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foC1 x?) (foC1 y?) (ScriptID 64020 0)
		)
		(localproc_0a3f)
	)
)

(instance foA5 of MountainPath
	(properties
		x 1458
		y 89
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1485 76 1506 104 1484 110 1460 72)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foD1 x?) (foD1 y?) (ScriptID 64020 0)
		)
		(localproc_0c21)
	)
)

(instance foB1 of MountainPath
	(properties
		x 1431
		y 29
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1457 6 1459 38 1431 37 1431 6)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foC2 x?) (foC2 y?) (ScriptID 64020 0)
		)
		(localproc_0a3f)
	)
)

(instance foB2 of MountainPath
	(properties
		x 1298
		y 35
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1300 17 1300 38 1280 38 1278 16)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foD1 x?) (foD1 y?) (ScriptID 64020 0)
		)
		(localproc_0c21)
	)
)

(instance foC1 of MountainPath
	(properties
		x 1194
		y 128
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init:)
		(self createPoly: 1194 106 1195 130 1180 131 1178 102)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foA4 x?) (foA4 y?) (ScriptID 64020 0)
		)
		(localproc_0458)
	)
)

(instance foC2 of MountainPath
	(properties
		x 1093
		y 104
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1100 65 1106 115 1068 107 1072 80)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foB1 x?) (foB1 y?) (ScriptID 64020 0)
		)
		(localproc_0893)
	)
)

(instance foD1 of MountainPath
	(properties
		x 1199
		y 29
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1215 9 1216 27 1193 27 1193 4)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foB2 x?) (foB2 y?) (ScriptID 64020 0)
		)
		(localproc_0893)
	)
)

(instance foD2 of MountainPath
	(properties
		x 1093
		y 133
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1030 106 1029 126 1055 126 1056 100)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foE1 x?) (foE1 y?) (ScriptID 64020 0)
		)
		(localproc_0f39)
	)
)

(instance foE1 of MountainPath
	(properties
		x 1083
		y 24
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 1066 9 1066 32 1094 32 1093 10)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foD2 x?) (foD2 y?) (ScriptID 64020 0)
		)
		(localproc_0c21)
	)
)

(instance foE2 of MountainPath
	(properties
		x 940
		y 23
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 951 12 952 40 928 38 930 7)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foF1 x?) (foF1 y?) (ScriptID 64020 0)
		)
		(localproc_10aa)
	)
)

(instance foF1 of MountainPath
	(properties
		x 682
		y 45
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 678 41 693 47 691 17 677 14)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foE2 x?) (foE2 y?) (ScriptID 64020 0)
		)
		(localproc_0f39)
	)
)

(instance foF2 of MountainPath
	(properties
		x 651
		y 14
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 664 27 671 1 631 1)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foG1 x?) (foG1 y?) (ScriptID 64020 0)
		)
		(localproc_1226)
	)
)

(instance foG1 of MountainPath
	(properties
		x 88
		y 49
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 88 51 113 22 92 4 74 21 72 52)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foF2 x?) (foF2 y?) (ScriptID 64020 0)
		)
		(localproc_10aa)
	)
)

(instance foG2 of MountainPath
	(properties
		x 109
		y 52
		BAD_SELECTOR 4
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 110 31 91 57 110 74 128 49)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foH1 x?) (foH1 y?) (ScriptID 64020 0)
		)
		(localproc_1390)
	)
)

(instance foH1 of MountainPath
	(properties
		x 194
		y 123
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 169 101 196 115 208 93 180 76)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foG2 x?) (foG2 y?) (ScriptID 64020 0)
		)
		(localproc_1226)
	)
)

(instance foH2 of MountainPath
	(properties
		x 183
		y 122
		BAD_SELECTOR 5
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 205 120 191 143 165 135 182 111)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foI1 x?) (foI1 y?) (ScriptID 64020 0)
		)
		(localproc_1503)
	)
)

(instance foI1 of MountainPath
	(properties
		x 112
		y 257
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 107 239 109 202 123 194 136 211 140 250)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foH2 x?) (foH2 y?) (ScriptID 64020 0)
		)
		(localproc_1390)
	)
)

(instance foI2 of MountainPath
	(properties
		x 112
		y 219
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 64 216 89 236 103 202 74 183)
	)
	
	(method (cue)
		(theGame handsOff:)
		(MonoOut {Whoo-o-oaah})
		(ego
			setMotion: MoveTo (foK1 x?) (foK1 y?) (ScriptID 64020 0)
		)
		(localproc_1cfa)
	)
)

(instance foI3 of MountainPath
	(properties
		x 203
		y 202
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 240 201 262 236 288 220 264 183)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foM1 x?) (foM1 y?) (ScriptID 64020 0)
		)
		(localproc_1f4a)
	)
)

(instance foI4 of MountainPath
	(properties
		x 1026
		y 201
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self
			createPoly: 1002 185 1001 213 1043 214 1023 192 1025 172
		)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foJ1 x?) (foJ1 y?) (ScriptID 64020 0)
		)
		(localproc_1961)
	)
)

(instance foJ1 of MountainPath
	(properties
		x 1021
		y 193
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 975 147 975 168 1012 168 998 152)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foI4 x?) (foI4 y?) (ScriptID 64020 0)
		)
		(localproc_1503)
	)
)

(instance foJ2 of MountainPath
	(properties
		x 729
		y 162
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 714 136 714 162 745 161 742 130)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foL2 x?) (foL2 y?) (ScriptID 64020 0)
		)
		(localproc_1dd6)
	)
)

(instance foJ3 of MountainPath
	(properties
		x 876
		y 71
		BAD_SELECTOR 7
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 867 57 867 82 897 82 896 51)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foP1 x?) (foP1 y?) (ScriptID 64020 0)
		)
		(localproc_249a)
	)
)

(instance foK1 of MountainPath
	(properties
		x 678
		y 100
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 687 69 687 106 673 106 673 70)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foL1 x?) (foL1 y?) (ScriptID 64020 0)
		)
		(localproc_1dd6)
	)
)

(instance foL1 of MountainPath
	(properties
		x 607
		y 105
		BAD_SELECTOR 4
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 611 105 623 132 636 132 635 105)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foJ2 x?) (foJ2 y?) (ScriptID 64020 0)
		)
		(localproc_1961)
	)
)

(instance foL2 of MountainPath
	(properties
		x 607
		y 105
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 616 76 616 108 602 108 601 74)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foK1 x?) (foK1 y?) (ScriptID 64020 0)
		)
		(localproc_1cfa)
	)
)

(instance foM1 of MountainPath
	(properties
		x 222
		y 187
		BAD_SELECTOR 5
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 229 162 251 187 240 206 209 179)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foI3 x?) (foI3 y?) (ScriptID 64020 0)
		)
		(localproc_1503)
	)
)

(instance foM2 of MountainPath
	(properties
		x 615
		y 56
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 589 42 590 65 623 65 622 42)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foN2 x?) (foN2 y?) (ScriptID 64020 0)
		)
		(localproc_2184)
	)
)

(instance foN1 of MountainPath
	(properties
		x 891
		y 37
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 897 22 874 43 887 59 911 38)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foP1 x?) (foP1 y?) (ScriptID 64020 0)
		)
		(localproc_249a)
	)
)

(instance foN2 of MountainPath
	(properties
		x 741
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 820 -1 741 -1 741 27)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foO1 x?) (foO1 y?) (ScriptID 64020 0)
		)
		(localproc_2328)
	)
)

(instance foO1 of MountainPath
	(properties
		x 388
		y 55
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 379 42 379 69 404 69 404 41)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foN2 x?) (foN2 y?) (ScriptID 64020 0)
		)
		(localproc_2184)
	)
)

(instance foO2 of MountainPath
	(properties
		x 275
		y 58
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 283 41 283 70 265 70 265 40)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foQ2 x?) (foQ2 y?) (ScriptID 64020 0)
		)
		(localproc_261f)
	)
)

(instance foP1 of MountainPath
	(properties
		x 310
		y 85
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 292 69 292 100 320 100 319 68)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foJ3 x?) (foJ3 y?) (ScriptID 64020 0)
		)
		(localproc_1961)
	)
)

(instance foP2 of MountainPath
	(properties
		x 208
		y 92
		BAD_SELECTOR 1
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 221 68 221 104 195 105 195 68)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foQ1 x?) (foQ1 y?) (ScriptID 64020 0)
		)
		(localproc_261f)
	)
)

(instance foQ1 of MountainPath
	(properties
		x 80
		y 120
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 65 109 76 137 104 121 93 94)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foP2 x?) (foP2 y?) (ScriptID 64020 0)
		)
		(localproc_249a)
	)
)

(instance foQ2 of MountainPath
	(properties
		x 32
		y 109
		BAD_SELECTOR 6
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 22 87 32 116 56 105 44 77)
	)
	
	(method (cue)
		(theGame handsOff:)
		(ego
			setMotion: MoveTo (foO2 x?) (foO2 y?) (ScriptID 64020 0)
		)
		(localproc_2328)
	)
)

(instance foWinner of MountainPath
	(properties
		y 173
		BAD_SELECTOR 5
	)
	
	(method (init)
		(super init: &rest)
		(self createPoly: 28 176 -3 178 -2 138)
	)
	
	(method (cue)
		(theGame handsOff:)
		(curRoom newRoom: -24736)
	)
)

(instance oLavaScrollPlane of TorScrollPlane
	(properties
		priority 20
		oBoogleFeatures 0
		oExtraPlanes 0
		oMainPlane 0
		addBoogleFeature 0
	)
	
	(method (nSeq)
		(AddPicAt self -24836 0 0)
		(AddPicAt self -24835 632 0)
		(AddPicAt self -24834 1264 0)
	)
)

(instance foots of TPSound
	(properties)
)

(instance roLavaMountain of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= plane
			(oLavaScrollPlane
				init: 1896 (thePlane doDouble:)
				yourself:
			)
		)
		(music1 pageSize: -25236)
		(foWinner init:)
		(ego oPanner: init: setScaler: Scaler 82 25 297 120)
		(theGame handsOn:)
		(= local0 0)
		(switch prevRoomNum
			(-25036
				(theGame handsOff:)
				(ego
					posn: 1900 272
					setMotion: MoveTo 1726 272 (ScriptID 64020 0)
				)
			)
			(else  (ego posn: 1726 272))
		)
		(= gSel_1206Sel_16 (ego cel:))
		(localproc_0458)
	)
	
	(method (doit)
		(if (!= gSel_1206Sel_16 (ego cel:))
			(cond 
				((== 4 (ego cel:))
					(if local2
						(foots lThumbLoop: -24831)
					else
						(foots lThumbLoop: -24833)
					)
				)
				((== 10 (ego cel:))
					(if local2
						(foots lThumbLoop: -24830)
					else
						(foots lThumbLoop: -24832)
					)
				)
			)
		)
		(= gSel_1206Sel_16 (ego cel:))
	)
	
	(method (setWander)
	)
	
	(method (intoPouch &tmp [temp0 2])
		(switch local0
			(0 (localproc_0458))
			(1 (localproc_0893))
			(2 (localproc_0a3f))
			(3 (localproc_0c21))
			(4 (localproc_0f39))
			(5 (localproc_10aa))
			(6 (localproc_1226))
			(7 (localproc_1390))
			(8 (localproc_1503))
			(9 (localproc_1961))
			(10 (localproc_1cfa))
			(11 (localproc_1dd6))
			(12 (localproc_1f4a))
			(13 (localproc_2184))
			(14 (localproc_2328))
			(15 (localproc_249a))
			(16 (localproc_261f))
			(else  (= local0 -1))
		)
		(++ local0)
	)
)
