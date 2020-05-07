;;; Sierra Script 1.0 - (do not remove this comment)

;These are event flags.  They are used to track certian On/Off actions throughout the game.
; 102 through 109 are not defined	
(enum 110
	fWornOut 			;110
	SHEMA_MAKING_ORDER 			;111
	SHEMA_BRINGS_ORDER 			;112
	OBTAINED_BAR_NOTE 			;113
	VISITED_GRAVEYARD_NIGHTTIME ;114	;NOTE: this VISITED flag is outside of the regular visited flags.
	ABDULLA_IS_AT_INN 			;115
	MET_ABDULLA 				;116
	TAVERN_THROWN_OUT 			;117
	TAVERN_DRUNK 				;118
	MET_SHOPKEEPER 				;119
	PURCHASED_FROM_DRYGOODS 	;120
	UNUSED_121 		;-----------event 121 is unused
	RENTED_ROOMATINN 			;122
	LEARNED_THIEF_PASSWORD 		;123
	fHungry		 				;124
	fStarving	 				;125
	ALLEY_ROBBED 				;126
	SIGNED_LOGBOOK 				;127
	SLEPT_AT_INN 				;128
	fSaveAllowed				;129	; game saving is disabled in certain rooms, like during battles.
	SEARCHED_LOL_COUCH 			;130
	SEARCHED_LOL_BASKET 		;131
	SEARCHED_LOL_PURSE 			;132
	STOLE_CANDLESTICKS 			;133
	SEARCHED_LOL_DESK 			;134
	UNCOVERED_BIRDCAGE 			;135
	OBTAINED_SPIREA_SEED 		;136
	SMASHED_FLOWER1 			;137
	SMASHED_FLOWER2 			;138
	SMASHED_FLOWER3 			;139
	PICKED_ERANA_FLOWERS 		;140
	LEARNED_CALM 				;141
	ERASMUS_WARPOUT 			;142
	WRONG_ANSWER 				;143
	SEARCHED_SHERIFF_DRAWER 	;144
	SEARCHED_SHERIFF_SAFE 		;145
	UNUSED_146		;----------- event 146 is unused
	OBTAINED_KOBOLD_KEY 		;147
	fHaveToadstools		 		;148	; replaced flag 174 in 1.200
	BEAR_CAVE_FIRST_ENTRY 		;149	; used to trigger the inital entrance message
	DEFEATED_KOBOLD 			;150
	KOBOLD_CHEST_UNLOCKED 		;151
	UNUSED_152		;----------- event 152 is unused
	KOBOLD_AWAKE 				;153
	ERASMUS_ATTACKED 			;154
	SAW_RED_SIGN 				;155
	SAW_GREEN_SIGN 				;156
	UNUSED_157		;----------- event 157 is unused	
	MANTRAY_LEFT 				;158
	MANTRAY_RIGHT 				;159
	BEAR_FRIENDLY 				;160
	BEAR_GONE 					;161
	UNUSED_162		;----------- unused
	UNUSED_163		;----------- events 162-163 are unused
	fGhostsAttack 				;164
	OBTAINED_TOADSTOOLS 		;165
	FIGHTING_KOBOLD 			;166
	MET_BEAR 					;167
	fGhostOil 					;168	;protected by Undead Unguent
	DEFEATED_BEAR 				;169
	SAVED_BARNARD 				;170
	MET_BONEHEAD 				;171
	BABAYAGA_HUT_OPEN 			;172
	BABAYAGA_FLY_AWAY 			;173
	KOBOLD_CASTING_REVERSAL 	;174	; originally used for checking if player has Kobold Toadstools
	KOBOLD_CHEST_SEARCHED 		;175
	ENTERED_FOX_ROAD_1 			;176
	ENTERED_FOX_ROAD_2 			;177)
	UNUSED_178		;----------- event 178 is unused
	STOLE_SHERIFF_VASE 			;179
	MOVED_SHERIFF_VASE 			;180
	STOLE_SHERIFF_CANDELABRA 	;181
	STOLE_OTTO_MUSIC_BOX 		;182
	UNCOVERED_SHERIFF_SAFE 		;183
	CRACKED_SHERIFF_SAFE 		;184
	UNUSED_185		;----------- event 185 is unused
	DANCED_FOR_FAIRIES 			;186
	DANCING_FOR_FAIRIES 		;187
	GOT_FAIRIES_ATTENTION 		;188
	GOT_KARL_ATTENTION 			;189
	CLIMBED_SPIREA_LEDGE 		;190
	EXITED_TOWN 				;191
	SPIREA_INACTIVE 			;192
	OTTO_BACK_TO_BED 			;193
	OPEN_MUSIC_BOX 				;194
	OTTO_CLOSES_MUSIC_BOX 		;195
	SHERIFF_AWAKENED 			;196
	MET_DRYAD 					;197
	DRYAD_AGREED_HELP 			;198
	EXITED_TOWN_2 				;199
	UNUSED_200		;----------- event 200 is unused
	DISPEL_LEARNED_RECIPE 		;201
	STAG_PRESENT 				;202
	STAG_HURT 					;203
	ANTWERP_SKY 				;204
	fOverloaded 		;205
	ANTWERP_SPLIT 				;206
	MET_FOX 					;207
	OBTAINED_GEM 				;208
	LOOKED_IN_PTERESA_NEST 		;209
	OBTAINED_RING 				;210
	FLAG_211 					; event 211 is tested outside Healer's house, but is never set
	PTERESA_LEFT_NEST 			;212
	WHACKED_BY_GIANT 			;213
	UNUSED_214		;----------- event 214 is unused
	OBTAINED_BARNARD_REWARD 	;215
	KOBOLD_CHEST_KNOWN 			;216
	fStableClean 				;217
	DISPEL_GAVE_DUST 			;218
	DISPEL_GAVE_FUR 			;219
	DISPEL_GAVE_FLOWERS 		;220
	DISPEL_GAVE_ACORN 			;221
	DISPEL_GAVE_WATER 			;222
	DISPEL_HEALER_MAKING_POTION ;223
	OBTAINED_DISPEL_POTION 		;224
	MET_HEALER 					;225
	STOLE_HEALER_POTIONS 		;226	; banned from Healer's hut
	OBTAINED_GREEN_FUR 			;227
	LEARNED_DETECT 				;228
	MET_STABLEMAN 				;229
	RETURNED_RING 				;230
	UNUSED_231		;----------- event 231 is unused
	DEFEATED_FIRST_GOBLIN 		;232	; "Wow! You threw that dead Goblin a long way!"
	fMonsterDazzled				;233
	UNUSED_234		;----------- event 234 is unused
	SPIED_THIEVES 				;235
	HENRY_LADDER_KNOWN 			;236
	DEFEATED_MINOTAUR 			;237	; He's not actually dead, just unconscious
	BRIGAND_GATE_OPEN 			;238
	FLAG_239 					;EVENT_FIGHTINGWEAPONMASTER?? Unsure
	HENRY_DEADLY_TP 			;240	; teleported dangerously away by henry.
	HENRY_SAFE_TP 				;241	; teleported away safely by Henry
	ASKED_TRIGGER_SCROLL 		;242
	FLAG_243 					; Event 243 is related to the Weapons Master
	BRIGANDS_BEHIND_LOG 		;244
	OBTAINED_BRUTUS_KEY 		;245
	LAKE_EASTER_EGG 			;246
	SAID_HIDEN_GOSEKE 			;247
	HERO_KILLED_IN_BATTLE 		;248
	DEFEATED_WEAPON_MASTER 		;249
	DEFEATED_FRED 				;250
	DEFEATED_FRED_89 			;251
	TROLL_DOOR_UNLOCKED 		;252
	TROLL_DOOR_OPEN 			;253
	HERO_SITTING 				;254
	ORDERED_DRINK 				;255
; Events 256-260 are related to Yorick's maze
	FLAG_256
	OPENING_LEADER_DOOR 		;257
	FLAG_258
	PULLED_CHAIN 				;259
	FLAG_260
	
	DEFEATED_BRUTUS 			;261
	LEARNED_TRIGGER 			;262
	FLAG_263					;263 (used in Yorrick's room)
	FLAG_264					;264 (used in brigandDoorFall
	FLAG_265					;265 (used in Yorrick's room) 
	ERASMUS_ASKED_MAZE_GAME 	;266
	JESTER_NO_MORE_TALKING 		;267
	BEFRIENDED_JESTER 			;268
	FLAG_269					; set in brigandHappyFace
	FLAG_270					; set in brigandFallSideways
	FLAG_271					;271 (tested in Yorrick's room)
	CLIMBED_HENRY_CLIFF			;272
	SAVED_ELSA 					;273
	FLAG_274					;274 used in hermitHits
	KNOCKED_HENRY_DOOR 			;275
	FLAG_276					;276 used in hermitHits
	FLAG_277					;277 used in healerGetNest
	CLIMBED_HEALER_TREE 		;278
	fHaveFaeryShrooms	 		;279
	FLAG_280					;280 (set in Kobold Flame Dart)
	FLAG_281					;281 (set in Kobold Flame Dart)
	KOBOLD_CHEST_EXPLODED 		;282
	FLAG_283					;283 (cleared in Kobold Flame Dart)
	UNUSED_284		;----------- 
	UNUSED_285		;----------- 
	UNUSED_286		;----------- 
	UNUSED_287		;----------- 
	UNUSED_288		;----------- 
	UNUSED_289		;----------- 
	UNUSED_290		;----------- 
	UNUSED_291		;----------- 
	UNUSED_292		;----------- 
	UNUSED_293		;----------- 
	UNUSED_294
	UNUSED_295
	UNUSED_296
	UNUSED_297
	UNUSED_298
	SLINK_WARNING 				;299
	SHAMEEN_SIGNALING 			;300
	SHEMA_ASKS_ORDER 			;301
	EATEN_AT_INN 				;302
	FLAG_303					;303 related to InnHeroTale (possibly never set?)
	STOP_FIGHTING_WEAPONMASTER 	;304
	MET_GREEN_MEEP 				;305
	UNUSED_306
	UNUSED_307
	UNUSED_308
	UNUSED_309
	UNUSED_310
	UNUSED_311
	TALKED_BEAR 				;312
	UNUSED_313
	UNUSED_314
	UNUSED_315
	UNUSED_316
	UNUSED_317
	UNUSED_318
	UNUSED_319
	UNUSED_320
	OFFERED_TRAINING 			;321
	FLAG_322
	FARMER_IS_EAST 				;323
	FLAG_324
	fBabaFrog 					;325	;original name
	fBabaCurse	 				;326
	fAteShrooms			 		;327
	fInMainGame 				;328	; This flag is set when we're in the game proper. Original name from QFG2's import script.
	fHaveFlyingWater 			;329
	fHaveLakeWater	 			;330
	DEFEATED_OGRE 				;331
	OPENED_OGRE_CHEST 			;332
	SEARCHED_OGRE_CHEST 		;333
	DISPEL_HEALER_KNOWS_RECIPE 	;334
	SEARCHED_FRED_TREASURE 		;335
	GAVE_GEM_BONEHEAD 			;336
	GIVE_UP_MAZE_GAME 			;337
	GAVE_HERMIT_RATION 			;338	; Can sleep at the Hermit's cave with this flag set
	UNUSED_339		;----------- 
	SHAVED_FRED 				;340
	OGRE_GONE 					;341	; Ogre disappears day after his death
	UNUSED_342		;----------- 
	UNUSED_343		;----------- 
	UNUSED_344		;----------- 
	HIGH_SPEED_HERO				;345 (unused in v1.200)
	UNUSED_346		;----------- 
	GENEROUS_TO_ABDULLA 		;347
	BRIGANDS_UNAWARE 			;348
	UNUSED_349		;----------- 
	UNUSED_350		;----------- 
	FLAG_351					;351 set in arenaMantrayFight after killing the mantaray
	HENRY_DOOR_OPEN 			;352
	ERASMUS_KNOWS_HERO_HAS_MAGIC ;353
	MET_GARGOYLE 				;354
	READ_BARNARD_BULLETIN 		;355
)