;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64001)
(include sci.sh)
(use Main)
(use DmInv)

(public
	oInvHandler 0
	oBoogleHandler 1
	proc64001_2 2
	proc64001_3 3
	proc64001_4 4
)

(procedure (proc64001_2)
	(if (not (ego has: 'LOOKUP_ERROR'))
		(return LOOKUP_ERROR)
	)
	(if (not (ego has: LOOKUP_ERROR)) (return LOOKUP_ERROR))
	(if (not (ego has: LOOKUP_ERROR)) (return LOOKUP_ERROR))
	(if (not (ego has: LOOKUP_ERROR)) (return ioWand))
	(if (not (ego has: LOOKUP_ERROR)) (return LOOKUP_ERROR))
	(if (not (ego has: LOOKUP_ERROR)) (return LOOKUP_ERROR))
	(return 0)
)

(procedure (proc64001_3 param1)
	(return
		(if
			(or
				(== param1 LOOKUP_ERROR)
				(== param1 LOOKUP_ERROR)
				(== param1 LOOKUP_ERROR)
				(== param1 LOOKUP_ERROR)
				(== param1 LOOKUP_ERROR)
				(== param1 LOOKUP_ERROR)
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (proc64001_4 &tmp temp0)
	(= temp0 0)
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(if (ego has: LOOKUP_ERROR) (++ temp0))
	(return temp0)
)

(instance oInvHandler of InvHandler
	(properties)
	
	(method (nScreenOrgX &tmp temp0 nHBorderSize temp2)
		(self
			add:
				1
				LOOKUP_ERROR
				0
				LOOKUP_ERROR
				1
				LOOKUP_ERROR
				2
				LOOKUP_ERROR
				3
				LOOKUP_ERROR
				4
				LOOKUP_ERROR
				5
				LOOKUP_ERROR
				6
				LOOKUP_ERROR
				7
				LOOKUP_ERROR
				8
				LOOKUP_ERROR
				9
				LOOKUP_ERROR
				10
				LOOKUP_ERROR
				11
				LOOKUP_ERROR
				12
				LOOKUP_ERROR
				13
				LOOKUP_ERROR
				14
				LOOKUP_ERROR
				15
				LOOKUP_ERROR
				16
				LOOKUP_ERROR
				17
				LOOKUP_ERROR
				18
				LOOKUP_ERROR
				19
				LOOKUP_ERROR
				20
				LOOKUP_ERROR
				21
				LOOKUP_ERROR
				22
				LOOKUP_ERROR
				23
				LOOKUP_ERROR
				24
				LOOKUP_ERROR
				25
				LOOKUP_ERROR
				26
				LOOKUP_ERROR
				27
				LOOKUP_ERROR
				28
				LOOKUP_ERROR
				29
				LOOKUP_ERROR
				30
				ioRosin
				31
				LOOKUP_ERROR
				32
				LOOKUP_ERROR
				33
				LOOKUP_ERROR
				34
				LOOKUP_ERROR
				35
				LOOKUP_ERROR
				36
				LOOKUP_ERROR
				37
				LOOKUP_ERROR
				38
				LOOKUP_ERROR
				39
				LOOKUP_ERROR
				40
				LOOKUP_ERROR
				41
				LOOKUP_ERROR
				42
				LOOKUP_ERROR
				43
				LOOKUP_ERROR
				44
				LOOKUP_ERROR
				45
				{ioPeat}
				46
				{oStepTile}
				47
				LOOKUP_ERROR
				48
				LOOKUP_ERROR
				49
				LOOKUP_ERROR
				50
				{ioStinkyCarpet}
				51
				LOOKUP_ERROR
				52
				LOOKUP_ERROR
				53
				{oCannonball5}
				54
				LOOKUP_ERROR
				55
				{oMagicBook}
				56
				LOOKUP_ERROR
				57
				LOOKUP_ERROR
				58
				LOOKUP_ERROR
				59
				{oBoogleLantern}
				60
				LOOKUP_ERROR
				61
				LOOKUP_ERROR
				62
				LOOKUP_ERROR
		)
		(= nHBorderSize (nHBorder size:))
		(= temp0 0)
		(while (< temp0 nHBorderSize)
			((= temp2 (nHBorder at: temp0)) voCheck: global100)
			(++ temp0)
		)
	)
)

(instance ioAx of InventItem
	(properties
		verb 2
		noun 2
		bHilited -5236
		oParent -5036
	)
)

(instance ioRope of InventItem
	(properties
		verb 4
		noun 3
		bHilited -5235
		oParent -5035
	)
)

(instance ioPouch of InventItem
	(properties
		verb 3
		noun 8
		bHilited -5234
		oParent -5034
	)
)

(instance ioInchworm of InventItem
	(properties
		verb 18
		noun 4
		bHilited -5233
		oParent -5033
	)
)

(instance ioChuckBerries of InventItem
	(properties
		verb 8
		noun 9
		bHilited -5232
		oParent -5032
	)
)

(instance ioSquareRoot of InventItem
	(properties
		verb 9
		noun 10
		bHilited -5231
		oParent -5031
	)
)

(instance ioSnails of InventItem
	(properties
		verb 10
		noun 11
		bHilited -5230
		oParent -5030
	)
)

(instance ioMoatScum of InventItem
	(properties
		verb 11
		noun 12
		bHilited -5229
		oParent -5029
	)
)

(instance ioShard of InventItem
	(properties
		verb 12
		noun 13
		bHilited -5228
		oParent -5028
	)
)

(instance ioEressdy of InventItem
	(properties
		verb 13
		noun 14
		bHilited -5227
		oParent -5027
	)
)

(instance ioSlugs of InventItem
	(properties
		verb 20
		noun 17
		bHilited -5225
		oParent -5025
	)
)

(instance ioPeat of InventItem
	(properties
		verb 21
		noun 18
		bHilited -5226
		oParent -5026
	)
)

(instance ioConsoleShard of InventItem
	(properties
		verb 22
		noun 19
		bHilited -5224
		oParent -5024
	)
)

(instance ioLeaf of InventItem
	(properties
		verb 23
		noun 20
		bHilited -5223
		oParent -5023
	)
)

(instance ioGuillotineTile of InventItem
	(properties
		verb 24
		noun 22
		bHilited -5222
		oParent -5022
	)
)

(instance ioStepTile of InventItem
	(properties
		verb 25
		noun 23
		bHilited -5221
		oParent -5021
	)
)

(instance ioTrivetTile of InventItem
	(properties
		verb 26
		noun 24
		bHilited -5220
		oParent -5020
	)
)

(instance ioSmallDoorTile of InventItem
	(properties
		verb 27
		noun 25
		bHilited -5219
		oParent -5019
	)
)

(instance ioTableTopTile of InventItem
	(properties
		verb 28
		noun 26
		bHilited -5218
		oParent -5018
	)
)

(instance ioWarningTile of InventItem
	(properties
		verb 29
		noun 27
		bHilited -5217
		oParent -5017
	)
)

(instance ioTubTile of InventItem
	(properties
		verb 30
		noun 28
		bHilited -5216
		oParent -5016
	)
)

(instance ioSeatTile of InventItem
	(properties
		verb 31
		noun 29
		bHilited -5215
		oParent -5015
	)
)

(instance ioFloorTile of InventItem
	(properties
		verb 32
		noun 30
		bHilited -5214
		oParent -5014
	)
)

(instance ioCleanTile of InventItem
	(properties
		verb 48
		noun 44
		bHilited -5187
		oParent -4988
	)
)

(instance ioClothespin of InventItem
	(properties
		verb 33
		noun 31
		bHilited -5213
		oParent -5013
	)
)

(instance ioBallInvite of InventItem
	(properties
		verb 34
		noun 32
		bHilited -5212
		oParent -5012
	)
)

(instance ioBeestLeg of InventItem
	(properties
		verb 35
		noun 33
		bHilited -5211
		oParent -5011
	)
)

(instance ioDragonPoo of InventItem
	(properties
		verb 36
		noun 34
		bHilited -5210
		oParent -5010
	)
)

(instance ioHaremPillow of InventItem
	(properties
		verb 37
		noun 35
		bHilited -5209
		oParent -5009
	)
)

(instance ioRedCarpet of InventItem
	(properties
		verb 38
		noun 36
		bHilited -5208
		oParent -5008
	)
)

(instance ioStinkyCarpet of InventItem
	(properties
		verb 39
		noun 37
		bHilited -5207
		oParent -5007
	)
)

(instance ioFan of InventItem
	(properties
		verb 40
		noun 38
		bHilited -5206
		oParent -5006
	)
)

(instance ioLocket of InventItem
	(properties
		verb 41
		noun 39
		bHilited -5205
		oParent -5005
	)
)

(instance ioOpenLocket of InventItem
	(properties
		verb 73
		noun 86
		bHilited -5174
		oParent -4981
	)
)

(instance ioKnife of InventItem
	(properties
		verb 51
		noun 58
		bHilited -5182
		oParent -4984
	)
)

(instance ioAmmonia of InventItem
	(properties
		verb 76
		noun 9876
		bHilited -5181
		oParent -4983
	)
)

(instance ioOpenAmmonia of InventItem
	(properties
		verb 49
		noun 71
		bHilited -5175
		oParent -4979
	)
)

(instance ioWrench of InventItem
	(properties
		verb 74
		noun 87
		bHilited -5178
		oParent -4978
	)
)

(instance ioCannonball1 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioCannonball2 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioCannonball3 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioCannonball4 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioCannonball5 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioCannonball6 of InventItem
	(properties
		verb 50
		noun 54
		bHilited -5177
		oParent -4977
	)
)

(instance ioSilkWorms of InventItem
	(properties
		verb 54
		noun 66
		bHilited -5204
		oParent -5004
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 55)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(55
				(ego get: LOOKUP_ERROR)
				({ioKnife} setTotalWidth: 55)
				(LOOKUP_ERROR setTotalWidth: 54)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ioPlaybill of InventItem
	(properties
		verb 55
		noun 59
		bHilited -5203
		oParent -5003
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 54)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(54
				(ego get: LOOKUP_ERROR)
				(LOOKUP_ERROR setTotalWidth: 55)
				(LOOKUP_ERROR setTotalWidth: 54)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ioBagpipes of InventItem
	(properties
		verb 63
		noun 47
		bHilited -5202
		oParent -5002
	)
)

(instance ioTopHat of InventItem
	(properties
		verb 57
		noun 68
		bHilited -5201
		oParent -5001
	)
)

(instance ioRabbit of InventItem
	(properties
		verb 58
		noun 61
		bHilited -5200
		oParent -5000
	)
)

(instance ioCane of InventItem
	(properties
		verb 59
		noun 53
		bHilited -5199
		oParent -4999
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 67)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(67
				(ego put: LOOKUP_ERROR)
				(ego get: LOOKUP_ERROR)
			)
		)
	)
)

(instance ioWand of InventItem
	(properties
		verb 60
		noun 70
		bHilited -5198
		oParent -4998
	)
)

(instance ioMagicBook of InventItem
	(properties
		verb 62
		noun 48
		bHilited -5197
		oParent -4997
	)
)

(instance ioCrystcorder of InventItem
	(properties
		verb 64
		noun 55
		bHilited -5196
		oParent -4996
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 65 66)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(65
				((ScriptID 64017 0) set: 137)
				(((ScriptID 64001 0) get: 53) moveTo: -1)
			)
			(66
				((ScriptID 64017 0) set: 137)
				(((ScriptID 64001 0) get: 54) moveTo: -1)
			)
		)
	)
)

(instance ioAudcryst of InventItem
	(properties
		verb 65
		noun 46
		bHilited -5195
		oParent -4996
	)
)

(instance ioShatteredShard of InventItem
	(properties
		verb 66
		noun 45
		bHilited -5194
		oParent -4995
	)
)

(instance ioSaw of InventItem
	(properties
		verb 67
		noun 65
		bHilited -5193
		oParent -4994
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 59)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(59
				(ego put: 'LOOKUP_ERROR')
				(ego get: LOOKUP_ERROR)
			)
		)
	)
)

(instance ioBow of InventItem
	(properties
		verb 68
		noun 49
		bHilited -5192
		oParent -4993
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 70)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(70
				(ego put: LOOKUP_ERROR)
				(ego put: LOOKUP_ERROR)
				(ego get: LOOKUP_ERROR)
			)
		)
	)
)

(instance ioRosin of InventItem
	(properties
		verb 70
		noun 63
		bHilited -5191
		oParent -4992
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 68)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(68
				(ego put: LOOKUP_ERROR)
				(ego put: LOOKUP_ERROR)
				(ego get: LOOKUP_ERROR)
			)
		)
	)
)

(instance ioSilkHanky of InventItem
	(properties
		verb 56
		noun 57
		bHilited -5190
		oParent -4991
	)
)

(instance ioMagicTrick of InventItem
	(properties
		verb 61
		noun 69
		bHilited -1
		oParent -1
	)
)

(instance ioRosinedBow of InventItem
	(properties
		verb 69
		noun 62
		bHilited -5188
		oParent -4989
	)
)

(instance ioDawburr of InventItem
	(properties
		verb 52
		noun 56
		bHilited -5183
		oParent -4985
	)
)

(instance ioSappyDawburr of InventItem
	(properties
		verb 53
		noun 64
		bHilited -5180
		oParent -4982
	)
)

(instance ioBoogleAx of InventItem
	(properties
		verb 14
		noun 7
		bHilited -5136
		oParent -4936
	)
)

(instance ioBoogleBox of InventItem
	(properties
		verb 15
		noun 15
		bHilited -5135
		oParent -4935
	)
)

(instance ioBoogleYoYo of InventItem
	(properties
		verb 46
		noun 43
		bHilited -5130
		oParent -4930
	)
)

(instance ioBoogleShovel of InventItem
	(properties
		verb 44
		noun 42
		bHilited -5132
		oParent -4932
	)
)

(instance ioBoogleLantern of InventItem
	(properties
		verb 45
		noun 41
		bHilited -5131
		oParent -4931
	)
)

(instance ioBoogleWorm of InventItem
	(properties
		verb 43
		noun 40
		bHilited -5133
		oParent -4933
	)
)

(instance ioBoogleRedCross of InventItem
	(properties
		verb 71
		noun 50
		bHilited -5129
		oParent -4929
	)
)

(instance oBoogleHandler of InvHandler
	(properties)
	
	(method (nScreenOrgX &tmp temp0 nHBorderSize temp2)
		(self
			add:
				0
				LOOKUP_ERROR
				1
				LOOKUP_ERROR
				3
				LOOKUP_ERROR
				4
				LOOKUP_ERROR
				5
				LOOKUP_ERROR
				2
				LOOKUP_ERROR
				6
				LOOKUP_ERROR
		)
		(= nHBorderSize (nHBorder size:))
		(= temp0 0)
		(while (< temp0 nHBorderSize)
			(if (not (= temp2 (nHBorder at: temp0))) (break))
			(temp2 voCheck: global100)
			(++ temp0)
		)
	)
)
